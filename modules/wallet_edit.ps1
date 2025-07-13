# === wallet_edit.ps1 ===
$walletFile = 'C:\Bots\LunoSimBot\wallet\wallet.json'

if (-Not (Test-Path $walletFile)) {
    Write-Warning 'wallet.json not found. Run the Doctor module first.'
    return
}

# Load current wallet
$wallet = Get-Content $walletFile | ConvertFrom-Json

Write-Host ''
Write-Host 'Current Wallet Holdings:'
foreach ($prop in $wallet.PSObject.Properties) {
    Write-Host ("- {0}: {1}" -f $prop.Name, $prop.Value)
}

Write-Host ''
Write-Host 'Enter each symbol you own (letters only) and its amount (use “.” as decimal).'
Write-Host 'When done, leave symbol blank and press Enter.'

# Setup for culture-invariant decimal parsing
$ci = [System.Globalization.CultureInfo]::InvariantCulture
$ns = [System.Globalization.NumberStyles]::Float

while ($true) {
    $sym = Read-Host 'Symbol (e.g. BTC)'
    if ($sym -eq '') { break }

    if ($sym -notmatch '^[A-Za-z]+$') {
        Write-Warning 'Invalid symbol. Use letters only (e.g. BTC, ETH).'
        continue
    }

    # Ensure symbol exists in wallet
    if (-Not ($wallet.PSObject.Properties.Name -contains $sym)) {
        $wallet | Add-Member -MemberType NoteProperty -Name $sym -Value 0
    }

    # Prompt for amount until valid
    while ($true) {
        $amtInput = Read-Host ("Amount of {0}" -f $sym)
        $parsed = [decimal]0
        if ([decimal]::TryParse($amtInput, $ns, $ci, [ref]$parsed)) {
            $wallet.$sym = $parsed
            break
        }
        else {
            Write-Warning 'Invalid number. Use digits and “.” as decimal separator (e.g. 0.1234).'
        }
    }
}

# Save updated wallet
$wallet | ConvertTo-Json -Depth 2 | Set-Content $walletFile

Write-Host ''
Write-Host 'Wallet updated successfully.'
Read-Host 'Press Enter to return to menu'
