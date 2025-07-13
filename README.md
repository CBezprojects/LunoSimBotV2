# LunoSimBot v0.4 — GUI-Only Edition

Welcome to the fully modular, self-healing, GUI-driven simulator bot for BTC/ETH on Luno.

## 🔧 Key Features
- 🖥️ GUI interface for all operations (no PowerShell input required)
- 💾 Wallet viewer and editor
- 🛡️ Health check system with auto-repair
- 📂 Backup system (in progress)
- 📊 PnL dashboard and simulated trade inputs (coming soon)
- 📜 Log viewer and session export

## 📁 Folder Structure
```
C:\Bots\LunoSimBot\
├── gui\               → gui_launcher.py (main GUI)
├── modules\           → core_auto.ps1, strategy tools
├── wallet\            → wallet.json
├── logs\              → session.log
├── backups\           → auto-created backups
├── doctor\            → healthcheck.ps1
├── run_gui.bat         → launches GUI with no terminal
```

## 🚀 How to Run
1. Double-click `run_gui.bat`
2. Interact through the graphical interface only
3. All updates are tracked via GUI logs and session files

## 📌 Version Notes
- CLI mode deprecated in favor of GUI-first control
- All PowerShell scripts are now backend modules
