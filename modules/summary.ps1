# === summary.ps1 ===

$mainRoot   = 'C:\Bots\LunoSimBot'
$config     = Get-Content (Join-Path $mainRoot 'config.json') | ConvertFrom-Json
$walletFile = Join-Path $mainRoot 'wallet\wallet.json'
$currency   = $config.currency
$symbols    = $config.symbols

# map symbols to Luno ticker pairs
$pairMap = @{ 'BTC' = 'XBTZAR'; 'ETH' = 'ETHZAR' }

if (-Not (Test-Path $walletFile)) {
    Write-Error "wallet.json not found. Run Option 4 to set your holdings first."
    return
}

$wallet = Get-Content $walletFile | ConvertFrom-Json
$total  = [decimal]0

Write-Host ''
Write-Host '=== Portfolio Summary ==='
foreach ($sym in $symbols) {
    $amt = [decimal]($wallet.$sym)
    if ($pairMap.ContainsKey($sym)) {
        try {
            $resp  = Invoke-RestMethod -Uri "https://api.luno.com/api/1/ticker?pair=$($pairMap[$sym])" -UseBasicParsing
            $price = [decimal]$resp.last_trade
        } catch {
            Write-Warning "Could not fetch live price for $sym"
            $price = 0
        }
    } else {
        $price = 0
    }

    $value = $amt * $price
    $total += $value

    Write-Host ("{0,-4} : {1,12:N8} × {2,12:N2} {3} = {4,12:N2} {3}" `
        -f $sym, $amt, $price, $currency, $value)
}

Write-Host ('-' * 50)
Write-Host ("Total Value    : {0,12:N2} {1}" -f $total, $currency)
Write-Host ''

Read-Host 'Press Enter to return to menu'
