# === main.ps1 ===
$ErrorActionPreference = "Stop"

# Use a dedicated variable for your bot’s root
$mainRoot = "C:\Bots\LunoSimBot"

# --- Load or create config ---
$configPath = Join-Path $mainRoot "config.json"
if (-Not (Test-Path $configPath)) {
    Write-Warning "config.json missing. Creating default config..."
    $defaultConfig = @{ mode = "sim"; symbols = @("BTC","ETH"); currency = "ZAR" }
    $defaultConfig | ConvertTo-Json -Depth 2 | Set-Content $configPath
    Write-Host "Default config.json created."
}

# --- Run Doctor Healthcheck ---
$doctorPath = Join-Path $mainRoot "doctor\healthcheck.ps1"
if (Test-Path $doctorPath) {
    Write-Host "Running Doctor Healthcheck..."
    . $doctorPath
} else {
    Write-Warning "Doctor module not found. Skipping healthcheck."
}

# --- Launch Core System (with debug) ---
$corePath = Join-Path $mainRoot "modules\core.ps1"
Write-Host "Debug: corePath = $corePath"
Write-Host "Debug: Test-Path(corePath) = $(Test-Path $corePath)"

if (Test-Path $corePath) {
    Write-Host "Core module found, launching..."
    . $corePath
} else {
    Write-Error "Core module missing at $corePath. Cannot proceed."
}
