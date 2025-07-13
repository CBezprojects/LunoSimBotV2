# === wallet_view.ps1 ===
$walletFile = "C:\Bots\LunoSimBot\wallet\wallet.json"

if (Test-Path $walletFile) {
    Write-Host ""
    Write-Host "Current Wallet Balances:"
    $wallet = Get-Content $walletFile | ConvertFrom-Json
    foreach ($asset in $wallet.PSObject.Properties) {
        Write-Host ("- {0}: {1}" -f $asset.Name, $asset.Value)
    }
}
else {
    Write-Warning "wallet.json not found. Run the Doctor module first to recreate it."
}

Write-Host ""
Read-Host "Press Enter to return to menu"
