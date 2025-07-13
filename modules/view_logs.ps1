# === view_logs.ps1 ===
$logFile = 'C:\Bots\LunoSimBot\logs\session.log'

if (Test-Path $logFile) {
    Write-Host ''
    Write-Host '=== Session Log ==='
    Get-Content $logFile | ForEach-Object { Write-Host $_ }
} else {
    Write-Warning 'No session.log found.'
}

Write-Host ''
Read-Host 'Press Enter to return to menu'
