@echo off
cd /d C:\Bots\LunoSimBot\gui
start "" "pythonw.exe" gui_launcher.py
exit
@echo on
rem This batch file changes the directory to the LunoSimBot GUI folder and starts the GUI   using pythonw.exe to avoid opening a command prompt window.
rem Make sure to adjust the path if your Python installation is in a different location.    