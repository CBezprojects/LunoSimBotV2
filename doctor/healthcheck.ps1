# === healthcheck.ps1 ===
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
$base = Join-Path $root ".."

function Test-PathEx($path, $type = "folder") {
    if (-Not (Test-Path $path)) {
        if ($type -eq "folder") {
            New-Item -Path $path -ItemType Directory -Force | Out-Null
            Write-Host "Created missing folder: $path"
        }
        elseif ($type -eq "file") {
            New-Item -Path $path -ItemType File -Force | Out-Null
            Write-Host "Created missing file: $path"
        }
    }
    else {
        Write-Host "Exists: $path"
    }
}

Write-Host "Running Doctor Healthcheck..."

# Required folders
$folders = @("wallet", "modules", "logs", "backups")
foreach ($folder in $folders) {
    Test-PathEx (Join-Path $base $folder) "folder"
}

# Required files
$configFile = Join-Path $base "config.json"
$walletFile = Join-Path $base "wallet\wallet.json"

if (-Not (Test-Path $configFile)) {
    Write-Host "config.json missing. Creating default..."
    $defaultConfig = @{ mode = "sim"; symbols = @("BTC", "ETH"); currency = "ZAR" }
    $defaultConfig | ConvertTo-Json | Set-Content $configFile
}

if (-Not (Test-Path $walletFile)) {
    Write-Host "wallet.json missing. Creating default wallet..."
    $defaultWallet = @{ BTC = 0.0; ETH = 0.0; ZAR = 10000.0; trades = @() }
    $defaultWallet | ConvertTo-Json | Set-Content $walletFile
}

Write-Host "Healthcheck complete."
