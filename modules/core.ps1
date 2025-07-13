# === core.ps1 ===
$mainRoot = 'C:\Bots\LunoSimBot'
$logPath  = Join-Path $mainRoot 'logs\session.log'

function Write-Log {
    param([string]$msg)
    $time = Get-Date -Format 'yyyy-MM-dd HH:mm:ss'
    $line = "$time | $msg"
    Add-Content -Path $logPath -Value $line
    Write-Host $line
}

Write-Log 'CORE SYSTEM INITIALIZED'

do {
    Write-Host ''
    Write-Host '=== LunoSimBot CLI ==='
    Write-Host '1. Run Doctor Checkup'
    Write-Host '2. View Wallet'
    Write-Host '3. Simulate Trade Loop'
    Write-Host '4. Update Wallet'
    Write-Host '5. View Portfolio Summary'
    Write-Host '6. View Logs'
    Write-Host '0. Exit'
    $choice = Read-Host 'Choose an option'

    switch ($choice) {
        '1' {
            . (Join-Path $mainRoot 'doctor\healthcheck.ps1')
            Write-Log 'Ran doctor module'
        }
        '2' {
            . (Join-Path $mainRoot 'modules\wallet_view.ps1')
            Write-Log 'Viewed wallet'
        }
        '3' {
            . (Join-Path $mainRoot 'modules\simulator.ps1')
            Write-Log 'Started simulation'
        }
        '4' {
            $edit = Join-Path $mainRoot 'modules\wallet_edit.ps1'
            if (Test-Path $edit) {
                . $edit
                Write-Log 'Edited wallet'
            }
    else {
        Write-Log 'Wallet edit module missing'
    }
}
'5' {
    . (Join-Path $mainRoot 'modules\summary.ps1')
    Write-Log 'Viewed portfolio summary'
}
        '0' {
            Write-Log 'Exiting system'
        }
        Default {
            Write-Host 'Invalid option.'
        }
    }
} while ($choice -ne '0')

Write-Host 'Goodbye!'
