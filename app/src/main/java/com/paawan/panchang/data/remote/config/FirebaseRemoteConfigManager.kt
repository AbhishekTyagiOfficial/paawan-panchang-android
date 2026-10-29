package com.paawan.panchang.data.remote.config

import android.util.Log
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.paawan.panchang.data.local.datastore.UserPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRemoteConfigManager @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
    private val preferencesRepository: UserPreferencesRepository
) {
    companion object {
        private const val TAG = "FirebaseRemoteConfig"
        const val KEY_OLD_DESIGN = "Old_Design"
    }

    private val scope = CoroutineScope(Dispatchers.IO)
    private val _rcOldDesign = MutableStateFlow<Boolean?>(null)
    val rcOldDesign: StateFlow<Boolean?> = _rcOldDesign.asStateFlow()

    fun fetchAndApply() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val isOld = remoteConfig.getBoolean(KEY_OLD_DESIGN)
                    Log.d(TAG, "Successfully fetched & activated Remote Config: $KEY_OLD_DESIGN = $isOld")
                    _rcOldDesign.value = isOld
                    scope.launch {
                        preferencesRepository.setOldDesign(isOld)
                    }
                } else {
                    Log.w(TAG, "Failed to fetch Remote Config", task.exception)
                }
            }

        // Real-time listener for instantaneous updates from Firebase Console
        remoteConfig.addOnConfigUpdateListener(object : ConfigUpdateListener {
            override fun onUpdate(configUpdate: ConfigUpdate) {
                if (configUpdate.updatedKeys.contains(KEY_OLD_DESIGN)) {
                    remoteConfig.activate().addOnCompleteListener { activateTask ->
                        if (activateTask.isSuccessful) {
                            val isOld = remoteConfig.getBoolean(KEY_OLD_DESIGN)
                            Log.d(TAG, "Real-time update received: $KEY_OLD_DESIGN = $isOld")
                            _rcOldDesign.value = isOld
                            scope.launch {
                                preferencesRepository.setOldDesign(isOld)
                            }
                        }
                    }
                }
            }

            override fun onError(error: FirebaseRemoteConfigException) {
                Log.w(TAG, "Remote Config update listener error", error)
            }
        })
    }
}
