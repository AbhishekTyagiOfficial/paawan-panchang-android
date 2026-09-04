@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%"=="" @echo off
@if "%JAVA_HOME%"=="" set "JAVA_HOME=C:\Program Files\Java\jdk-21"
set DIRNAME=%~dp0
if "%DIRNAME%"=="" set DIRNAME=.
set CMD_LINE_ARGS=
:winLoop
if "%1"=="" goto doneStart
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto winLoop
:doneStart
"%DIRNAME%..\..\..\..\.gradle\wrapper\dists\gradle-8.14-bin\38aieal9i53h9rfe7vjup95b9\gradle-8.14\bin\gradle.bat" %CMD_LINE_ARGS%
