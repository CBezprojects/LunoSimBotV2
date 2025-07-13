# ✅ LunoSimBot v0.4 GUI-Only To-Do List

## ✅ Completed
- Wallet Editor GUI with self-healing
- GUI launcher (`gui_launcher.py`) as core control panel
- GUI-based health check via `doctor/healthcheck.ps1`
- Terminal-free `.bat` launcher (`run_gui.bat`)

---

## 🔜 In Progress / Pending

### 1. Backup Button (GUI-Based)
- Create "Backup Now" button in GUI
- Save wallet.json, config.json, trade_log.json (if exist)
- Auto-create backups folder
- Timestamped naming

### 2. Doctor.ps1 Upgrade
- Validate all folders + critical scripts exist
- Warn/fix any corrupt or missing parts
- GUI-compatible output

### 3. PnL Dashboard Tab
- View current value of wallet
- Live ZAR conversion
- Optional Excel export

### 4. Simulated Trade Tab
- Manual Buy/Sell entry
- Update wallet + write to trade_log.json
- Validate balances

### 5. Log Viewer Tab
- Reads latest session logs into GUI tab
- Refresh button
- Auto-create empty log file if missing

### 6. core_auto.ps1
- New strategy script with **no CLI loop**
- Used only by GUI
- Replaces `core.ps1` in GUI

### 7. Self-Healing Pass (Final)
- Every important file/folder validated at GUI launch
- Auto-create or reset safely if missing

### 8. Final Doctor.ps1 Pass
- Scan/validate every .ps1 and .json
- Check for corrupted code or structure
- Optional: repair suggestions or inline auto-fixes
