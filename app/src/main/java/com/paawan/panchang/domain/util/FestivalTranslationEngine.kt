package com.paawan.panchang.domain.util

import com.paawan.panchang.domain.model.Festival

data class LanguageOption(
    val code: String,
    val name: String,
    val flag: String
)

data class FestivalFullTranslation(
    val title: String,
    val dateLabel: String,
    val tithiLabel: String,
    val muhuratLabel: String,
    val kathaHeader: String,
    val description: String,
    val vidhiHeader: String,
    val pujaVidhi: String,
    val mantrasHeader: String,
    val mantras: List<String>
)

object FestivalTranslationEngine {

    val supportedLanguages = listOf(
        LanguageOption("hi", "हिंदी (Hindi)", "🇮🇳"),
        LanguageOption("en", "English", "🇬🇧"),
        LanguageOption("es", "Español (Spanish)", "🇪🇸"),
        LanguageOption("kr", "한국어 (Korean)", "🇰🇷"),
        LanguageOption("ur", "اردو (Urdu)", "🇵🇰"),
        LanguageOption("fr", "Français (French)", "🇫🇷"),
        LanguageOption("de", "Deutsch (German)", "🇩🇪"),
        LanguageOption("ja", "日本語 (Japanese)", "🇯🇵"),
        LanguageOption("ru", "Русский (Russian)", "🇷🇺"),
        LanguageOption("ar", "العربية (Arabic)", "🇸🇦")
    )

    fun getTranslation(festival: Festival, langCode: String): FestivalFullTranslation {
        val dateLabel = when (langCode) {
            "en", "fr" -> "Date"
            "es" -> "Fecha"
            "kr" -> "날짜"
            "ur" -> "تاریخ"
            "de" -> "Datum"
            "ja" -> "日付"
            "ru" -> "Дата"
            "ar" -> "التاريخ"
            else -> "दिनांक"
        }

        val tithiLabel = when (langCode) {
            "en", "fr", "es", "de" -> "Tithi"
            "kr" -> "티티 (달의 날)"
            "ur" -> "تتھی"
            "ja" -> "ティティ"
            "ru" -> "Титхи"
            "ar" -> "التيثي"
            else -> "तिथि"
        }

        val muhuratLabel = when (langCode) {
            "en" -> "Auspicious Muhurat"
            "es" -> "Horario Sagrado"
            "kr" -> "길한 시간 (무후랏)"
            "ur" -> "مبارک مہورت"
            "fr" -> "Horaire Auspicieux"
            "de" -> "Günstige Zeit"
            "ja" -> "吉兆の時間"
            "ru" -> "Благоприятное время"
            "ar" -> "الوقت المبارك"
            else -> "शुभ मुहूर्त"
        }

        val kathaHeader = when (langCode) {
            "en" -> "Significance & Story"
            "es" -> "Significado e Historia Sagrada"
            "kr" -> "전설적인 이야기와 신성한 의미"
            "ur" -> "اہمیت اور مذہبی کہانی"
            "fr" -> "Signification et Histoire Sacrée"
            "de" -> "Bedeutung und Heilige Geschichte"
            "ja" -> "神聖な物語と意義"
            "ru" -> "Значение и Священное Сказание"
            "ar" -> "الأهمية والقصة المقدسة"
            else -> "पौराणिक कथा व महत्व"
        }

        val vidhiHeader = when (langCode) {
            "en" -> "Puja Vidhi & Rituals"
            "es" -> "Rituales de Adoración Paso a Paso"
            "kr" -> "기도 및 의식 절차"
            "ur" -> "پوجا کا طریقہ اور قواعد"
            "fr" -> "Rituels de Culte et Étapes"
            "de" -> "Rituale und Anbetungsschritte"
            "ja" -> "礼拝の儀式と手順"
            "ru" -> "Ритуалы и Порядок Поклонения"
            "ar" -> "طقوس العبادة والخطوات"
            else -> "विस्तृत पूजा विधि"
        }

        val mantrasHeader = when (langCode) {
            "en" -> "Sacred Mantras"
            "es" -> "Mantras Sagrados"
            "kr" -> "신성한 주문과 기도문"
            "ur" -> "مقدس منتر"
            "fr" -> "Mantras Sacrés"
            "de" -> "Heilige Mantras"
            "ja" -> "マントラと真言"
            "ru" -> "Священные Мантры"
            "ar" -> "الترانيم المقدسة"
            else -> "शुभ मंत्र व आरती"
        }

        return when (festival.id) {
            "fest_1" -> getGaneshChaturthi(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_2" -> getAnantChaturdashi(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_3" -> getPitruPaksha(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_4" -> getNavratri(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_5" -> getMahaNavami(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_6" -> getDussehra(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_7" -> getKarwaChauth(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_8" -> getDhanteras(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_9" -> getDiwali(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_10" -> getGovardhanPuja(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            "fest_11" -> getChhathPuja(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
            else -> getGenericTranslation(langCode, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getGaneshChaturthi(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "श्री गणेश चतुर्थी",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "गणेश चतुर्थी भगवान श्री गणेश जी का पावन जन्मोत्सव पर्व है। भगवान गणेश को बुद्धि, समृद्धि और समस्त संकटों को हरने वाले विघ्नहर्ता के रूप में पूजा जाता है। माता पार्वती ने हल्दी के उबटन से श्री गणेश की रचना कर उनमें प्राण फूंके थे। भगवान शिव ने उन्हें गजानन रूप प्रदान कर समस्त देवी-देवताओं में सर्वप्रथम पूजे जाने का अमर वरदान दिया।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. पूजा स्थल को स्वच्छ कर ईशान कोण में भगवान श्री गणेश की प्रतिमा स्थापित करें।\n2. उन्हें मोदक का भोग, 21 दूर्वा दल तथा लाल गुड़हल का पुष्प अर्पित करें।\n3. शुद्ध घी का दीपक व धूप प्रज्वलित करें।\n4. गणपति अथर्वशीर्ष का पाठ करें और सपरिवार आरती उतारें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ गं गणपतये नमः", "वक्रतुण्ड महाकाय सूर्यकोटि समप्रभ। निर्विघ्नं कुरु मे देव सर्वकार्येषु सर्वदा॥")
            )
            "en" -> FestivalFullTranslation(
                title = "Ganesh Chaturthi (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Ganesh Chaturthi celebrates the divine birth of Lord Ganesha, the remover of all obstacles and god of wisdom and prosperity. Goddess Parvati created Ganesha from turmeric and bestowed life upon him. When Ganesha guarded her dwelling, Lord Shiva placed an elephant head upon him and granted the supreme boon that Ganesha will be worshipped first among all deities.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Clean the sanctuary and establish Ganesha's idol in North-East (Ishan Kon).\n2. Offer Modak sweets, 21 blades of Durva grass, and red Hibiscus flowers.\n3. Light Ghee lamp and incense.\n4. Recite Ganesha Atharvashirsha and perform Aarti.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "es" -> FestivalFullTranslation(
                title = "Ganesh Chaturthi (Fiesta del Señor Ganesha)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Ganesh Chaturthi celebra el nacimiento divino del Señor Ganesha, el dios que elimina todos los obstáculos y otorga sabiduría. La Diosa Parvati le dio vida para proteger su hogar. El Señor Shiva lo bendijo otorgándole una cabeza de elefante y el honor primordial de ser adorado en primer lugar en todos los rituales sagrados.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Limpie el santuario y coloque la figura del Señor Ganesha mirando al noreste.\n2. Ofrezca dulces Modak, hierba Durva sagrada y flores rojas de hibisco.\n3. Encienda una lámpara de Ghee e incienso.\n4. Recite oraciones devocionales y realice el ritual Aarti.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "kr" -> FestivalFullTranslation(
                title = "가네샤 차투르티 (Ganesh Chaturthi)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "가네샤 차투르티는 모든 장애물을 제거하고 지혜와 번영을 가져다주는 가네샤 신의 신성한 탄생을 축하하는 축제입니다. 시바 신은 가네샤에게 코끼리 머리를 부여하고 모든 의식에서 가장 먼저 예배받는 최고의 축복을 내렸습니다.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 제단을 청소하고 북동쪽에 가네샤 신상을 모십니다.\n2. 달콤한 모닥 과자와 두르바 풀, 붉은 꽃을 바칩니다.\n3. 향과 등불을 켜고 가네샤 찬가를 독송합니다.\n4. 가족과 함께 아르티 기도 의식을 올립니다.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "ur" -> FestivalFullTranslation(
                title = "گنیش چتورتھی (Ganesh Chaturthi)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "گنیش چتورتھی بھگوان گنیش کی پیدائش کا مقدس تیوہار ہے جو حکمت اور تمام رکاوٹوں کو دور کرنے والے دیوتا ہیں۔ بھگوان شیو نے گنیش کو ہاتھی کا سر عطا کر کے تمام پوجاؤں میں سب سے پہلے پوجے جانے کا بردان دیا۔",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. پوجا کی جگہ صاف کر کے بت قائم کریں۔\n2. مودک مٹھائی، دوروا گھاس اور لال پھول پیش کریں۔\n3. گھی کا دیپک جلائیں۔\n4. خاندان کے ساتھ مل کر آرتی کریں۔",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "fr" -> FestivalFullTranslation(
                title = "Ganesh Chaturthi (Fête du Seigneur Ganesha)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Ganesh Chaturthi célèbre la naissance sacrée du Seigneur Ganesha, dieu de la sagesse et destructeur des obstacles. Le Seigneur Shiva lui a accordé une tête d'éléphant et la bénédiction suprême d'être vénéré en premier dans tous les rituels.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Nettoyez le sanctuaire et installez l'idole au Nord-Est.\n2. Offrez des friandises Modak, de l'herbe Durva et des fleurs rouges.\n3. Allumez une lampe au Ghee.\n4. Récitez les prières et accomplissez l'Aarti.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "de" -> FestivalFullTranslation(
                title = "Ganesh Chaturthi (Fest des Gottes Ganesha)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Ganesh Chaturthi feiert die Geburt von Lord Ganesha, dem Beseitiger aller Hindernisse. Lord Shiva schenkte ihm einen Elefantenkopf und den Segen, bei allen Ritualen zuerst verehrt zu werden.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Reinigen Sie den Altar und stellen Sie die Statue im Nordosten auf.\n2. Opfern Sie Modak-Süßigkeiten, Durva-Gras und rote Blumen.\n3. Zünden Sie eine Ghee-Lampe an.\n4. Singen Sie Mantras und führen Sie die Aarti-Zeremonie durch.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "ja" -> FestivalFullTranslation(
                title = "ガネーシャ・チャトゥルティ (Ganesh Chaturthi)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "ガネーシャ・チャトゥルティは障害を取り除き知恵と繁栄をもたらすガネーシャ神の誕生を祝う神聖な祭りです。シヴァ神から象の頭を授けられ、すべての儀式で最初に礼拝される祝福を受けました。",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 祭壇を清め、北東の方角にガネーシャ神像を安置します。\n2. モーダカ菓子、ドゥールヴァ草、赤い花を捧げます。\n3. 灯明を灯しマントラを唱えます。\n4. 家族と共にアールティの儀式を行います。",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "ru" -> FestivalFullTranslation(
                title = "Ганеша Чатуртхи (Ganesh Chaturthi)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Ганеша Чатуртхи — праздник рождения Господа Ганеши, устранителя всех препятствий. Господь Шива даровал Ганеше слоновью голову и благословение почитаться первым перед всеми божествами.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Очистите алтарь и установите божество на северо-востоке.\n2. Предложите сладости Модак, Траву Дурва и красные цветы.\n3. Зажгите лампу с маслом Гхи и благовония.\n4. Пойте священные мантры и совершайте Аарти.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            "ar" -> FestivalFullTranslation(
                title = "غانيش تشاتورثي (Ganesh Chaturthi)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "مهرجان غانيش تشاتورثي يحتفل بلميلاد المقدس للإله غانيش، مزيل العقبات وإله الحكمة والازدهار.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. نظف مكان العبادة وضِع التمثال في اتجاه الشمال الشرقي.\n2. قدم حلوى الموداك والزهور الحمراء.\n3. أشعل مصباح السمن والبخور.\n4. اتل الترانيم وقم بطقوس الآرتي.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Gan Ganapataye Namaha", "Vakratunda Mahakaya Surya Koti Samaprabha")
            )
            else -> FestivalFullTranslation(
                title = "${festival.titleHi} (${festival.titleEn})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader, description = festival.description,
                vidhiHeader = vidhiHeader, pujaVidhi = festival.pujaVidhi,
                mantrasHeader = mantrasHeader, mantras = festival.mantras
            )
        }
    }

    private fun getAnantChaturdashi(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "अनंत चतुर्दशी (गणेश विसर्जन)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "अनंत चतुर्दशी का पावन पर्व भगवान श्री विष्णु के अनंत स्वरूप की पूजा तथा 10 दिनों के गणेशोत्सव के उपरांत भगवान गणेश के भव्य विसर्जन के रूप में मनाया जाता है। इस दिन अनंत सूत्र बांधने से जीवन के समस्त दुःखों और कष्टों का निवारण होता है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. दाहिने हाथ की कलाई में 14 गांठों वाला पवित्र अनंत सूत्र धारण करें।\n2. शेषनाग पर विराजमान भगवान श्री हरि विष्णु की विधिवत पूजा करें।\n3. गाजे-बाजे के साथ भगवान गणेश की विसर्जन शोभायात्रा निकालें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ अनंताय नमः", "शान्ताकारं भुजगशयनं पद्मनाभं सुरेशम्")
            )
            "en" -> FestivalFullTranslation(
                title = "Anant Chaturdashi (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Anant Chaturdashi is dedicated to worshipping Lord Ananta (the infinite form of Lord Vishnu) and marks the grand Ganesha Visarjan after 10 days of Ganeshotsav.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Fast and tie the sacred Anant Sutra (cotton thread with 14 knots) on the right wrist.\n2. Worship Lord Vishnu on Sheshnag.\n3. Perform farewell Visarjan procession for Lord Ganesha with chants.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Anantaya Namaha", "Shantakaram Bhujagashayanam")
            )
            "es" -> FestivalFullTranslation(
                title = "Anant Chaturdashi (Día Infinito del Señor Vishnu)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Día sagrado dedicado al Señor Ananta (forma infinita del Señor Vishnu) y despedida de las estatuas del Señor Ganesha tras 10 días de festividades.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Ate el cordón sagrado Anant Sutra de 14 nudos en la muñeca derecha.\n2. Adore al Señor Vishnu sobre Sheshnag.\n3. Realice la procesión de despedida de Ganesha.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Anantaya Namaha", "Shantakaram Bhujagashayanam")
            )
            "kr" -> FestivalFullTranslation(
                title = "아난트 차투르다시 (Anant Chaturdashi)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "비슈누 신의 무한한 형상인 아난타 신을 찬양하며 10일간의 가네샤 축제를 마무리하고 가네샤 신상을 물에 모시는 의식의 날입니다.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 14개의 매듭이 있는 신성한 아난트 실을 오른쪽 손목에 맵니다.\n2. 비슈누 신께 기도하고 가네샤 신상 환송 행렬을 진행합니다.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Anantaya Namaha", "Shantakaram Bhujagashayanam")
            )
            "ur" -> FestivalFullTranslation(
                title = "اننت چتوردشی (Anant Chaturdashi)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "بھگوان وشنو کے اننت روپ کی پوجا اور گنیش وسرجن کا مقدس دن۔",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. دائیں کلائی پر 14 گرہوں والا مقدس اننت دھاگا باندھیں۔\n2. بھگوان وشنو کی پوجا کر کے گنیش وسرجن کریں۔",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Anantaya Namaha", "Shantakaram Bhujagashayanam")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getPitruPaksha(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "पितृ पक्ष प्रारंभ (श्राद्ध पक्ष)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "पितृ पक्ष (श्राद्ध पक्ष) 16 दिनों की वह पावन अवधि है जिसमें पूर्वजों (पितरों) के प्रति कृतज्ञता व्यक्त करते हुए तर्पण, पिंड दान और श्राद्ध कर्म किया जाता है। इससे पितृ दोष से मुक्ति मिलती है और पितरों का अमर आशीर्वाद प्राप्त होता है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. कुतप काल (अपराह्न) में दक्षिण दिशा की ओर मुख करके पितरों को जल और काले तिल से तर्पण दें।\n2. ब्राह्मणों एवं जरूरतमंदों को सात्विक भोजन कराएं।\n3. कौवे, गाय और कुत्ते के लिए विशेष भोजन ग्रास निकालें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ पितृभ्यो नमः", "देवताभ्यः पितृभ्यश्च महायोगिभ्य एव च। नमः स्वाहायै स्वधायै नित्यमेव नमो नमः॥")
            )
            "en" -> FestivalFullTranslation(
                title = "Pitru Paksha Begins (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Pitru Paksha is a 16-day sacred fortnight dedicated to paying homage to ancestors through Tarpan, Pind Daan, and Shraddh rituals.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Offer water and black sesame (Tarpan) facing South during Kutapa Kaal.\n2. Feed needy and Brahmins with pure vegetarian meals.\n3. Offer food portions for crows, cows, and dogs.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Pitrubhyo Namaha", "Devatabhya Pitrubhyascha Mahayogibhye Eva Cha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getNavratri(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "शारदीय नवरात्रि (नवदुर्गा पूजा)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "नवरात्रि शक्ति की अधिष्ठात्री देवी मां दुर्गा के नौ पवित्र रूपों की उपासना का महान उत्सव है। मां दुर्गा ने महिषासुर का वध कर धर्म की स्थापना की थी। नौ दिनों में नवदुर्गा (शैलपुत्री, ब्रह्मचारिणी, चंद्रघंटा, कुष्मांडा, स्कंदमाता, कात्यायनी, कालरात्रि, महागौरी, सिद्धिदात्री) की पूजा की जाती है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. प्रातकाल शुभ मुहूर्त में घटस्थापना (कलश स्थापना) करें।\n2. नौ दिनों तक अखण्ड ज्योति प्रज्वलित रखें।\n3. नित्य दुर्गा सप्तशती का पाठ करें।\n4. मां दुर्गा को लाल चुनरी, नारियल और शृंगार सामग्री अर्पित करें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("सर्वमंगल मंगल्ये शिवे सर्वार्थ साधिके। शरण्ये त्र्यंबके गौरि नारायणि नमोऽस्तु ते॥", "ॐ दुं दुर्गायै नमः")
            )
            "en" -> FestivalFullTranslation(
                title = "Shardiya Navratri (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Navratri marks the nine sacred nights celebrating Goddess Durga's triumph over Mahishasura. Each night honors one of the Navadurga forms: Shailaputri, Brahmacharini, Chandraghanta, Kushmanda, Skandamata, Katyayani, Kalaratri, Mahagauri, and Siddhidatri.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Perform Ghatasthapana during early morning Muhurat.\n2. Light Akhand Jyoti (uninterrupted lamp) for 9 days.\n3. Recite Durga Saptashati daily.\n4. Offer Coconut, Chunri, and Red flowers to Maa Durga.",
                mantrasHeader = mantrasHeader, mantras = listOf("Sarva Mangala Mangalye Shive Sarvartha Sadhike", "Om Dum Durgayei Namaha")
            )
            "es" -> FestivalFullTranslation(
                title = "Shardiya Navratri (Nueve Noches Sagradas)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Navratri celebra las nueve noches victoriosas de la Diosa Durga sobre el demonio Mahishasura. Durante cada noche se honra a una de las nueve manifestaciones divinas de la Diosa Durga (Navadurga).",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Realice la ceremonia Ghatasthapana por la mañana.\n2. Mantenga una lámpara sagrada encendida durante los 9 días.\n3. Recite oraciones a Durga Saptashati diariamente.\n4. Ofrezca flores rojas, coco y mantos sagrados a la Diosa Durga.",
                mantrasHeader = mantrasHeader, mantras = listOf("Sarva Mangala Mangalye Shive Sarvartha Sadhike", "Om Dum Durgayei Namaha")
            )
            "kr" -> FestivalFullTranslation(
                title = "샤르디야 나브라트리 (Shardiya Navratri)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "나브라트리는 두르가 여신이 악마 마히샤수라를 물리친 승리를 기념하는 9일간의 신성한 축제입니다. 9일 동안 두르가 여신의 9가지 신성한 형상을 독송하며 예배합니다.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 아침 길한 시간에 가타스타파나 항아리 의식을 진행합니다.\n2. 9일 동안 꺼지지 않는 신성한 등불을 켭니다.\n3. 두르가 사프타샤티경을 독송합니다.\n4. 붉은 꽃과 코코넛을 바칩니다.",
                mantrasHeader = mantrasHeader, mantras = listOf("Sarva Mangala Mangalye Shive Sarvartha Sadhike", "Om Dum Durgayei Namaha")
            )
            "ur" -> FestivalFullTranslation(
                title = "شاردیہ نوراتری (Shardiya Navratri)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "نوراتری دیوی درگا کے نو مقدس روپوں کی عبادت کا تیوہار ہے جس میں بدی پر نیکی کی فتح کا جشن منایا جاتا ہے۔",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. صبح گھٹ استھاپنا کی رسم ادا کریں۔\n2. نو دن تک ورت رکھیں اور چراغ روشن رکھیں۔\n3. روزانہ درگا سپت شتی پڑھیں۔\n4. دیوی درگا کو لال چنری اور ناریل پیش کریں۔",
                mantrasHeader = mantrasHeader, mantras = listOf("Sarva Mangala Mangalye Shive Sarvartha Sadhike", "Om Dum Durgayei Namaha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getMahaNavami(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "महानवमी (कन्या पूजन)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "शारदीय नवरात्रि की नवमी तिथि को मां सिद्धिदात्री की पूजा के साथ 9 छोटी कन्याओं को देवी रूप मानकर कन्या पूजन किया जाता है। कन्याओं को भोजन कराने से मां दुर्गा का परम आशीर्वाद प्राप्त होता है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. नौ कन्याओं और एक बटुक (भैरव) के चरण धोकर तिलक लगाएं।\n2. उन्हें हलवा, पूरी और काले चने का प्रसाद भरपेट खिलाएं।\n3. सामर्थ्यानुसार दक्षिणा और उपहार देकर चरण स्पर्श कर आशीर्वाद लें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ ह्रीं सह सिद्धिदात्र्यै नमः", "या देवी सर्वभूतेषु मां सिद्धिदात्री रूपेण संस्थिता। नमस्तस्यै नमस्तस्यै नमस्तस्यै नमो नमः॥")
            )
            "en" -> FestivalFullTranslation(
                title = "Maha Navami (Kanya Pujan)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Maha Navami marks the ninth day of Navratri dedicated to Maa Siddhidatri. Nine young girls representing Navadurga are worshipped with Halwa, Puri, and Chana feast.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Wash feet of 9 young girls and apply Tilak.\n2. Serve Prasad of Puri, Chana, and Suji Halwa.\n3. Offer gifts, coins, and seek sacred blessings.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Hreem Saha Siddhidatryai Namaha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getDussehra(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "विजयादशमी (दशहरा)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "दशहरा (विजयादशमी) अधर्म पर धर्म और असत्य पर सत्य की विजय का प्रतीक है। इसी पावन तिथि पर मर्यादा पुरुषोत्तम भगवान श्री राम ने अहंकारी रावण का वध किया था तथा मां दुर्गा ने महिषासुर का संहार किया था।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. विजय मुहूर्त में शमी वृक्ष एवं अपराजिता देवी का पूजन करें।\n2. अस्त्र-शस्त्र, वाहनों तथा कार्य उपकरणों का पूजन (शस्त्र पूजन) करें।\n3. संध्याकाल में रावण, कुंभकर्ण एवं मेघनाद के पुतलों का दहन करें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("श्री राम राम रामेति रमे रामे मनोरमे। सहस्रनाम तत्तुल्यं रामनाम वरानने॥", "ॐ अपराजितायै नमः")
            )
            "en" -> FestivalFullTranslation(
                title = "Dussehra - Vijayadashami (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Dussehra (Vijayadashami) symbolizes the victory of righteousness over evil. It commemorates Lord Rama's slaying of the demon king Ravana and Goddess Durga's destruction of Mahishasura after ten days of fierce battle.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Perform Shami Tree Pujan and Aparajita Devi Pujan during Vijay Muhurat.\n2. Worship vehicles, tools, and weapons (Shastra Pujan).\n3. Burn effigies of Ravana, Kumbhakarna, and Meghnada in evening.",
                mantrasHeader = mantrasHeader, mantras = listOf("Sri Rama Rama Rameti Rame Rame Manorame", "Om Aparajitayai Namaha")
            )
            "es" -> FestivalFullTranslation(
                title = "Dussehra (Día de la Victoria Sagrada)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Dussehra simboliza el triunfo del bien sobre el mal. Celebra la victoria del Señor Rama sobre el demonio Ravana y de la Diosa Durga sobre Mahishasura.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Adore al árbol Shami y a la Diosa Aparajita.\n2. Realice la bendición de vehículos y herramientas de trabajo.\n3. Presencie la quema ceremonial de las efigies de Ravana al anochecer.",
                mantrasHeader = mantrasHeader, mantras = listOf("Sri Rama Rama Rameti Rame Rame Manorame", "Om Aparajitayai Namaha")
            )
            "kr" -> FestivalFullTranslation(
                title = "두세라 (비자야다샤미 - Dussehra)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "두세라는 악에 대한 선의 승리를 상징하는 축제입니다. 라마 신이 악마 라바나를 퇴치하고 두르가 여신이 마히샤수라를 무찌른 승리의 날입니다.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 아파라지타 여신과 샤미 나무에 기도를 드립니다.\n2. 차량 및 작업 도구를 축복하는 의식을 진행합니다.\n3. 저녁에 라바나 인형 화형식을 관람합니다.",
                mantrasHeader = mantrasHeader, mantras = listOf("Sri Rama Rama Rameti Rame Rame Manorame", "Om Aparajitayai Namaha")
            )
            "ur" -> FestivalFullTranslation(
                title = "دشہرا - وجیادشمی (Dussehra)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "دشہرا بدی پر نیکی کی فتح کی علامت ہے۔ اس دن بھگوان رام نے راون کو شکست دی تھی۔",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. شامی درخت اور اپراجتا دیوی کی پوجا کریں۔\n2. گاڑیوں اور اوزاروں کی پوجا کریں۔\n3. شام کے وقت راون کا پتلا جلائیں۔",
                mantrasHeader = mantrasHeader, mantras = listOf("Sri Rama Rama Rameti Rame Rame Manorame", "Om Aparajitayai Namaha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getKarwaChauth(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "करवा चौथ (अखंड सौभाग्य व्रत)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "करवा चौथ सुहागिन महिलाओं द्वारा पति की लंबी आयु, उत्तम स्वास्थ्य और अखंड सौभाग्य की कामना के लिए रखा जाने वाला अत्यंत कठिन निर्जला व्रत है। इसमें भगवान शिव, माता पार्वती, श्री गणेश और चंद्र देव की पूजा की जाती है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. सूर्योदय से पूर्व सरगी का सेवन करें।\n2. दिनभर निर्जला व्रत रखकर सायं समय करवा चौथ की व्रत कथा सुनें।\n3. चंद्रोदय के समय छलनी से चंद्रमा के दर्शन कर अर्घ्य दें और पति के हाथों जल ग्रहण कर व्रत खोलें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ नमः शिवाय", "नमः शिवायै शर्वाण्यै सौभाग्यं संततिं शुभाम्। प्रयच्छ भक्तियुक्तानां नारीणां हरवल्लभे॥")
            )
            "en" -> FestivalFullTranslation(
                title = "Karwa Chauth (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Karwa Chauth is a sacred waterless fast (Nirjala Vrat) observed by married women for the health, long life, and well-being of their husbands.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Eat Sargahi before sunrise.\n2. Observe waterless fast during the day and listen to Karwa Chauth Katha in the evening.\n3. View the Moon through a sieve, offer Arghya to Chandra Dev, and break fast from husband's hands.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Namah Shivaya", "Om Ganeshayai Namaha")
            )
            "es" -> FestivalFullTranslation(
                title = "Karwa Chauth (Ayuno Sagrado del Matrimonio)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Karwa Chauth es un ayuno sagrado sin agua realizado por mujeres casadas por la longevidad, salud y prosperidad de sus esposos.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Consuma comida Sargahi antes del amanecer.\n2. Ayune sin agua durante el día y escuche la historia sagrada por la tarde.\n3. Mire la Luna a través de un tamiz, ofrezca agua a la Luna y rompa el ayuno.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Namah Shivaya", "Om Ganeshayai Namaha")
            )
            "kr" -> FestivalFullTranslation(
                title = "카르와 초트 (Karwa Chauth)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "카르와 초트는 기혼 여성들이 남편의 건강과 장수를 기원하며 낮 동안 물 한 모금 마시지 않고 올리는 신성한 단식 기도의 날입니다.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 일출 전 사르가히 음식을 섭취합니다.\n2. 낮 동안 단식하며 저녁에 전설 이야기를 듣습니다.\n3. 체를 통해 달을 바라보고 달님께 물을 올린 뒤 단식을 마칩니다.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Namah Shivaya", "Om Ganeshayai Namaha")
            )
            "ur" -> FestivalFullTranslation(
                title = "کروا چوتھ (Karwa Chauth)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "کروا چوتھ شادی شدہ عورتوں کا اپنے شوہر کی لمبی عمر کے لیے بغیر پانی کے مقدس ورت ہے۔",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. سورج نکلنے سے پہلے سرگاہی کھائیں۔\n2. دن بھر ورت رکھیں اور شام کو کہانی سنیں۔\n3. چھلنی سے چاند دیکھ کر ارگھیہ دیں اور ورت کھولیں۔",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Namah Shivaya", "Om Ganeshayai Namaha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getDhanteras(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "धनतेरस (धन्वंतरि जयंती व कुबेर पूजा)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "दीपावली महापर्व का प्रथम दिन धनतेरस आरोग्य के देवता भगवान धन्वंतरि और धन के स्वामी कुबेर जी की पूजा के लिए समर्पित है। इस दिन सोना, चांदी, पीतल के बर्तन या धनिया खरीदना अत्यंत शुभ माना जाता है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. संध्याकाल में घर के बाहर यमराज के नाम का चौमुखी दीपक (यमदीपम) प्रज्वलित करें।\n2. भगवान धन्वंतरि और श्री कुबेर जी का पूजन करें।\n3. क्रय किए गए नए बर्तन या आभूषण की पूजा कर मां लक्ष्मी का स्मरण करें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ नमो भगवते वासुदेवाय धन्वंतरये नमः", "ॐ यक्षाय कुबेराय वैश्रवणाय धनधान्याधिपतये नमः")
            )
            "en" -> FestivalFullTranslation(
                title = "Dhanteras (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Dhanteras marks the first day of Diwali festivities dedicated to Lord Dhanvantari (god of health) and Lord Kuber. People purchase new utensils, gold, or brass for good fortune.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Light Yamadeepam (four-faced oil lamp) outside facing South in evening.\n2. Worship Lord Dhanvantari and Kuber Dev with sweets and incense.\n3. Sanctify newly bought metal items with Akshat and Tilak.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Namo Bhagavate Vasudevaya Dhanvantaraye Namaha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getDiwali(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "दीपावली (लक्ष्मी-गणेश पूजन)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "दीपावली अंधकार पर प्रकाश और अज्ञान पर ज्ञान की विजय का महापर्व है। 14 वर्ष के वनवास और रावण वध के पश्चात भगवान श्री राम के अयोध्या आगमन पर नगरवासियों ने दीपमालाएं जलाकर स्वागत किया था। इसी रात्रि समुद्र मंथन से मां लक्ष्मी का प्राकट्य भी हुआ था।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. घर के मुख्य द्वार पर रंगोली सजाएं और तोरण लगाएं।\n2. प्रदोष काल में मां लक्ष्मी, श्री गणेश और कुबेर जी का पूजन करें।\n3. खील, बताशे, कमल का फूल, मिष्ठान्न व चांदी का सिक्का अर्पित करें।\n4. सरसों के तेल व घी के 13 या 21 मिट्टी के दीपक घर के बाहर प्रज्वलित करें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ श्रीं ह्रीं श्रीं कमले कमलालये प्रसीद प्रसीद ॐ श्रीं ह्रीं श्रीं महालक्ष्म्यै नमः", "ॐ गं गणपतये नमः")
            )
            "en" -> FestivalFullTranslation(
                title = "Diwali (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Diwali, the festival of lights, celebrates Lord Rama's triumphant return to Ayodhya after 14 years of exile and defeating Ravana. The citizens illuminated Ayodhya with millions of earthen lamps (Diyas). It is also the night Goddess Lakshmi emerged from Samudra Manthan to bestow prosperity upon humanity.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Clean and decorate home entrance with colorful Rangoli and flowers.\n2. Perform Lakshmi-Ganesha Pujan during Pradosh Kaal.\n3. Offer Kheel, Batasha, Lotus flowers, sweets, and silver coin to Goddess Lakshmi.\n4. Light 13 or 21 earthen Diyas filled with mustard oil/Ghee outside.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Shreem Hreem Shreem Kamale Kamalalaye Praseeda", "Om Shreem Maha Lakshmiyei Namaha")
            )
            "es" -> FestivalFullTranslation(
                title = "Diwali (Festival de las Luces)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Diwali celebra el victorioso regreso del Señor Rama a Ayodhya tras 14 años de exilio. Los habitantes iluminaron toda la ciudad con millones de lámparas de arcilla. También se venera la llegada de la Diosa Lakshmi, quien otorga riqueza, salud y prosperidad espiritual.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Limpie el hogar y cree hermosas figuras Rangoli en la entrada.\n2. Realice la adoración a Lakshmi y Ganesha al atardecer.\n3. Ofrezca flores de loto, dulces sagrados y monedas de plata.\n4. Encienda lámparas de aceite de arcilla (Diyas) alrededor de la casa.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Shreem Hreem Shreem Kamale Kamalalaye Praseeda", "Om Shreem Maha Lakshmiyei Namaha")
            )
            "kr" -> FestivalFullTranslation(
                title = "디왈리 (빛의 축제 - Diwali)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "디왈리는 라마 신이 14년간의 유배를 마치고 아요디아로 귀환한 승리를 기념하는 빛의 축제입니다. 아요디아 시민들은 수많은 흙 등불을 켜서 라마 신을 맞이했습니다. 또한 이 신성한 밤에 풍요와 번영의 락슈미 여신이 세상에 은혜를 내립니다.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 집안을 청소하고 입구에 알록달록한 랑골리 그림을 그립니다.\n2. 해질녘에 락슈미 여신과 가네샤 신께 기도를 바칩니다.\n3. 연꽃과 신성한 과자, 은동전을 신전에 바칩니다.\n4. 집 안팎으로 전통 흙 등불(디야)을 밝게 켭니다.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Shreem Hreem Shreem Kamale Kamalalaye Praseeda", "Om Shreem Maha Lakshmiyei Namaha")
            )
            "ur" -> FestivalFullTranslation(
                title = "دیوالی (دیپکوں کا تیوہار - Diwali)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "دیوالی بھگوان رام کی 14 سالہ بن واس کے بعد ایودھیا واپسی کی خوشی میں منایا جاتا ہے۔ اس رات دیوی لکشمی کی پوجا کر کے خوشحالی اور برکت کی دعا کی جاتی ہے۔",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. گھر کی صفائی کر کے رنگولی سے سجھائیں۔\n2. شام کے وقت لکشمی گنیش پوجا کریں۔\n3. مٹھائی، ناریل اور چاندی کے سکے پیش کریں۔\n4. گھر کے باہر دیئے جلائیں۔",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Shreem Hreem Shreem Kamale Kamalalaye Praseeda", "Om Shreem Maha Lakshmiyei Namaha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getGovardhanPuja(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "गोवर्धन पूजा (अन्नकूट महापर्व)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "गोवर्धन पूजा भगवान श्री कृष्ण द्वारा इंद्र देव के प्रकोप और मूसलाधार वर्षा से गोकुलवासियों की रक्षा हेतु गोवर्धन पर्वत को अपनी कनिष्ठिका उंगली पर उठाने की स्मृति में मनाई जाती है। इस दिन अन्नकूट का भोग लगाया जाता है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. गाय के गोबर से गोवर्धन पर्वत की आकृति बनाकर फूलों से सजाएं।\n2. 56 प्रकार के व्यंजनों (अन्नकूट) का भोग भगवान श्री कृष्ण को लगाएं।\n3. गोवर्धन पर्वत और गायों की पूजा कर परिक्रमा करें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("गोवर्धन धारी श्री कृष्ण मुरारी", "ॐ नमो भगवते वासुदेवाय नमः")
            )
            "en" -> FestivalFullTranslation(
                title = "Govardhan Puja (Annakut Mahotsav)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Govardhan Puja commemorates Lord Krishna lifting the Govardhan Hill on his little finger to protect Gokul villagers from torrential rains sent by Indra.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Create Govardhan hill form using cow dung and decorate with flowers.\n2. Offer 56 food delicacies (Annakut Bhog) to Lord Krishna.\n3. Perform Parikrama and worship cows.",
                mantrasHeader = mantrasHeader, mantras = listOf("Govardhan Dhari Krishna Murari", "Om Namo Bhagavate Vasudevaya")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getChhathPuja(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        return when (lang) {
            "hi" -> FestivalFullTranslation(
                title = "छठ पूजा (सूर्य षष्ठी महापर्व)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "छठ पूजा भगवान सूर्य देव और छठी मइया की उपासना का अत्यंत पवित्र 4 दिवसीय वैदिक महापर्व है। यह पर्व प्रकृति, जल, वायु और जीवन देने वाले सूर्य देव के प्रति परम कृतज्ञता प्रकट करने के लिए मनाया जाता है।",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. प्रथम दिन नहाय-खाय एवं द्वितीय दिन खरना का व्रत करें।\n2. तृतीय दिन नदी/पोखर के जल में खड़े होकर अस्ताचलगामी (ढलते) सूर्य को संध्या अर्घ्य अर्पित करें।\n3. चतुर्थ दिन उदीयमान (उगते) सूर्य को प्रातः अर्घ्य देकर ठेकुआ प्रसाद वितरण के साथ व्रत का पारण करें।",
                mantrasHeader = mantrasHeader,
                mantras = listOf("ॐ सूर्याय नमः", "ॐ घृणिः सूर्याय नमः", "एहि सूर्य सहस्त्रांशो तेजोराशे जगत्पते। अनुकंपय मां भक्त्या गृहाणार्घ्यं दिवाकर॥")
            )
            "en" -> FestivalFullTranslation(
                title = "Chhath Puja (${festival.titleHi})",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Chhath Puja is an ancient 4-day Vedic festival dedicated to Surya Dev (the Sun God) and Chhathi Maiya to express gratitude for sustaining life on Earth.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Nahay Khay on 1st day & Kharna on 2nd day.\n2. Stand waist-deep in river water and offer Evening Arghya (Sandhya Arghya) to setting Sun on 3rd day.\n3. Offer Morning Arghya (Usha Arghya) to rising Sun on 4th day with Thekua offerings.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Suryaya Namaha", "Om Ghrini Suryaya Namaha")
            )
            "es" -> FestivalFullTranslation(
                title = "Chhath Puja (Adoración al Dios Sol)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "Chhath Puja es un antiguo festival védico de 4 días dedicado al Dios Sol (Surya Dev) para agradecer el sustento de la vida en la Tierra.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. Ritual de purificación Nahay Khay el día 1 y Kharna el día 2.\n2. Entre al agua del río hasta la cintura y ofrezca agua al Sol poniente el día 3.\n3. Ofrezca oraciones al Sol naciente al amanecer el día 4.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Suryaya Namaha", "Om Ghrini Suryaya Namaha")
            )
            "kr" -> FestivalFullTranslation(
                title = "찻 푸자 (Chhath Puja)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "찻 푸자는 4일간 진행되는 베다 축제로 태양신(수리야 데브)께 생명의 은혜에 대한 감사를 올리는 축제입니다.",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. 1일차 정화 의식과 2일차 카르나 의식을 올립니다.\n2. 3일차 지는 해를 향해 강물에 들어가 기도를 바칩니다.\n3. 4일차 일출 시 떠오르는 태양에 물을 올리며 단식을 마칩니다.",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Suryaya Namaha", "Om Ghrini Suryaya Namaha")
            )
            "ur" -> FestivalFullTranslation(
                title = "چھٹھ پوجا (Chhath Puja)",
                dateLabel = dateLabel, tithiLabel = tithiLabel, muhuratLabel = muhuratLabel,
                kathaHeader = kathaHeader,
                description = "چھٹھ پوجا سورج دیوتا کی عبادت کا 4 روزہ قدیم تیوہار ہے۔",
                vidhiHeader = vidhiHeader,
                pujaVidhi = "1. نہائے کھائے اور کھرنا کی رسم ادا کریں۔\n2. ندیمیں کھڑے ہو کر ڈوبتے سورج کو ارگھیہ دیں۔\n3. صبح نکلتے سورج کو ارگھیہ دے کر ورت کھولیں۔",
                mantrasHeader = mantrasHeader, mantras = listOf("Om Suryaya Namaha", "Om Ghrini Suryaya Namaha")
            )
            else -> getGenericTranslation(lang, dateLabel, tithiLabel, muhuratLabel, kathaHeader, vidhiHeader, mantrasHeader, festival)
        }
    }

    private fun getGenericTranslation(
        lang: String, dateLabel: String, tithiLabel: String, muhuratLabel: String,
        kathaHeader: String, vidhiHeader: String, mantrasHeader: String, festival: Festival
    ): FestivalFullTranslation {
        val title = when (lang) {
            "hi" -> "${festival.titleHi}"
            "en" -> "${festival.titleEn} (${festival.titleHi})"
            "es" -> "${festival.titleEn} - Fiesta Sagrada"
            "kr" -> "${festival.titleEn} (신성한 축제)"
            "ur" -> "${festival.titleHi} (${festival.titleEn})"
            "fr" -> "${festival.titleEn} (Fête Sacrée)"
            "de" -> "${festival.titleEn} (Heiliges Fest)"
            "ja" -> "${festival.titleEn} (神聖な祭日)"
            "ru" -> "${festival.titleEn} (Священный Праздник)"
            "ar" -> "${festival.titleEn} (مهرجان مقدس)"
            else -> "${festival.titleHi} (${festival.titleEn})"
        }

        val description = when (lang) {
            "hi" -> "पावन पर्व ${festival.titleHi}। सनातन संस्कृति का यह अत्यंत मंगलकारी एवं शुभ त्यौहार है।"
            "en" -> "Spiritual celebration of ${festival.titleEn}. ${festival.description}"
            "es" -> "Celebración espiritual sagrada de ${festival.titleEn}. ${festival.description}"
            "kr" -> "${festival.titleEn} 신성한 축제의 날입니다. ${festival.description}"
            "ur" -> "${festival.titleHi} کا مقدس تیوہار۔ ${festival.description}"
            "fr" -> "Célébration spirituelle sacrée de ${festival.titleEn}. ${festival.description}"
            "de" -> "Heilige spirituelle Feier von ${festival.titleEn}. ${festival.description}"
            "ja" -> "${festival.titleEn} の神聖な祝いの日です。${festival.description}"
            "ru" -> "Священное духовное празднование ${festival.titleEn}. ${festival.description}"
            "ar" -> "الاحتفال الروحي المقدس لـ ${festival.titleEn}. ${festival.description}"
            else -> "पावन पर्व ${festival.titleHi}।"
        }

        val pujaVidhi = when (lang) {
            "hi" -> "1. शुद्ध मन एवं पवित्र भाव से पूजा स्थल को स्वच्छ करें।\n2. धूप, दीप प्रज्वलित कर पुष्प, अक्षत एवं मिष्ठान्न अर्पित करें।\n3. भक्तिभाव से मंत्रोच्चार व आरती करें।"
            "en" -> "Perform sacred worship with flowers, incense, sweets, and devotional prayers. ${festival.pujaVidhi}"
            "es" -> "Realice la adoración sagrada con flores, incienso, dulces y oraciones devocionales. ${festival.pujaVidhi}"
            "kr" -> "신성한 꽃과 음식, 향을 바치며 정성껏 기도를 올립니다. ${festival.pujaVidhi}"
            "ur" -> "پاک صاف ہو کر لال پھول اور مٹھائی پیش کریں اور دعا کریں۔ ${festival.pujaVidhi}"
            "fr" -> "Effectuez le culte sacré avec des fleurs, de l'encens et des prières dévotes. ${festival.pujaVidhi}"
            "de" -> "Führen Sie die heilige Anbetung mit Blumen, Weihrauch und Gebeten durch. ${festival.pujaVidhi}"
            "ja" -> "神聖な花と香、お菓子を捧げて心を込めて祈ります。${festival.pujaVidhi}"
            "ru" -> "Совершайте священное поклонение с цветами, благовониями и молитвами. ${festival.pujaVidhi}"
            "ar" -> "قم بالعبادة المقدسة مع الزهور والبخور والصلوات الإيمانية. ${festival.pujaVidhi}"
            else -> "1. शुद्ध मन से पूजा स्थल को स्वच्छ करें।\n2. धूप-दीप जलाकर आरती करें।"
        }

        return FestivalFullTranslation(
            title = title,
            dateLabel = dateLabel,
            tithiLabel = tithiLabel,
            muhuratLabel = muhuratLabel,
            kathaHeader = kathaHeader,
            description = description,
            vidhiHeader = vidhiHeader,
            pujaVidhi = pujaVidhi,
            mantrasHeader = mantrasHeader,
            mantras = festival.mantras
        )
    }
}
