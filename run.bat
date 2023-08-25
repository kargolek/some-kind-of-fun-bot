@echo off
setlocal

REM Set the number of iterations
set iterations=200

REM Set the delay in seconds (15 minutes = 900 seconds)
set delay=450

call mvn clean

for /l %%i in (1,1,%iterations%) do (
    echo Running iteration %%i
    call mvn test -Dbrowser=local-chrome -Dheadless=disable

    REM Add a delay between iterations
    timeout /t %delay% /nobreak
)

endlocal
