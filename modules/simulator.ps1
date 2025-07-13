# === simulator.ps1 ===
# Live‐price fetcher for BTC and ETH via Luno public API

$mainRoot = 'C:\Bots\LunoSimBot'
$config   = Get-Content (Join-Path $mainRoot 'config.json') | ConvertFrom-Json
$currency = $config.currency
$symbols  = $config.symbols

# Map your symbols to Luno pair codes
$pairMap = @{
    'BTC' = 'XBTZAR'
    'ETH' = 'ETHZAR'
}

Write-Host ''
Write-Host 'Fetching live prices (press Ctrl+C to stop)...'
Write-Host ''

try {
    while ($true) {
        foreach ($symbol in $symbols) {
            if ($pairMap.ContainsKey($symbol)) {
                $pair = $pairMap[$symbol]
                $url  = "https://api.luno.com/api/1/ticker?pair=$pair"

                try {
                    $resp  = Invoke-RestMethod -Uri $url -UseBasicParsing
                    $price = [decimal]$resp.last_trade
                    Write-Host ("{0}: {1:N2} {2}" -f $symbol, $price, $currency)
                }
                catch {
                    Write-Warning ("Failed to fetch {0}: {1}" -f $symbol, $_.Exception.Message)
                }
            }
            else {
                Write-Warning ("No API mapping for symbol '{0}'" -f $symbol)
            }
        }
        Start-Sleep -Seconds 5
    }
}
catch [System.Management.Automation.StopUpsteamException] {
    Write-Host ''
    Write-Host 'Price fetch stopped.'
}
