# CODEBREAKER 2.0 RESET SCRIPT
$root = "C:\Bots\LunoSimBot"

# 🧨 Nuke everything but config, logs, and launcher
$preserve = @("config.json", "run_bot.ps1", "reset.ps1", "logs")
Get-ChildItem -Path $root -Recurse | Where-Object {
    $_.PSIsContainer -or ($preserve -notcontains $_.Name)
} | Remove-Item -Force -Recurse -ErrorAction SilentlyContinue

# 🏗️ Rebuild folders
$folders = @("modules", "wallet", "logs", "backups", "doctor")
foreach ($f in $folders) {
    New-Item -Path "$root\$f" -ItemType Directory -Force | Out-Null
}
Write-Host "✅ RESET COMPLETE. System cleaned and modular folders created."
