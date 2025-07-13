
import tkinter as tk
from tkinter import ttk, scrolledtext, messagebox
import threading, os, time, json, shutil

# === Paths ===
BASE_DIR = r"C:\Bots\LunoSimBot"
BOT_DIR = os.path.join(BASE_DIR, "bot")
WALLET_PATH = os.path.join(BOT_DIR, "wallet.json")
CONFIG_PATH = os.path.join(BOT_DIR, "config.json")
BACKUP_DIR = os.path.join(BOT_DIR, "backups")
EXPORT_PATH = os.path.join(BASE_DIR, "bot_export.xlsx")

# === Bot Logic Placeholder ===
def run_bot():
    try:
        with open(CONFIG_PATH) as f:
            config = json.load(f)
        with open(WALLET_PATH) as f:
            wallet = json.load(f)
        log("Bot running with config + wallet OK")
        # Simulate bot work
        while run_flag["running"]:
            time.sleep(1)
            log("Simulating trade...")
    except Exception as e:
        log(f"ERROR: {str(e)}")

def backup_wallet():
    try:
        if not os.path.exists(BACKUP_DIR):
            os.makedirs(BACKUP_DIR)
        timestamp = time.strftime("%Y%m%d-%H%M%S")
        backup_file = os.path.join(BACKUP_DIR, f"wallet_backup_{timestamp}.json")
        shutil.copy2(WALLET_PATH, backup_file)
        log("Backup saved.")
    except Exception as e:
        log(f"Backup failed: {str(e)}")

# === GUI Actions ===
def start_bot():
    if not os.path.exists(CONFIG_PATH) or not os.path.exists(WALLET_PATH):
        log("Missing config or wallet. Cannot start bot.")
        return
    run_flag["running"] = True
    t = threading.Thread(target=run_bot, daemon=True)
    t.start()
    log("Bot started.")

def pause_bot():
    run_flag["running"] = False
    backup_wallet()
    log("Bot paused and backup complete.")

def open_export():
    try:
        os.startfile(EXPORT_PATH)
    except Exception as e:
        log(f"Failed to open export: {str(e)}")

def open_folder():
    try:
        os.startfile(BOT_DIR)
    except Exception as e:
        log(f"Failed to open folder: {str(e)}")

def log(msg):
    log_console.insert(tk.END, f">> {msg}\n")
    log_console.see(tk.END)

# === App Window ===
root = tk.Tk()
root.title("LunoSimBot v0.4 GUI Mode")
root.geometry("700x400")

run_flag = {"running": False}

frame = ttk.Frame(root, padding=10)
frame.pack(fill=tk.BOTH, expand=True)

button_frame = ttk.Frame(frame)
button_frame.pack(fill=tk.X, pady=5)

ttk.Button(button_frame, text="▶️ Start Bot", command=start_bot).pack(side=tk.LEFT, padx=5)
ttk.Button(button_frame, text="⏸ Pause Bot", command=pause_bot).pack(side=tk.LEFT, padx=5)
ttk.Button(button_frame, text="💾 Backup Wallet", command=backup_wallet).pack(side=tk.LEFT, padx=5)
ttk.Button(button_frame, text="📁 Open Export", command=open_export).pack(side=tk.LEFT, padx=5)
ttk.Button(button_frame, text="⚙️ Open Folder", command=open_folder).pack(side=tk.LEFT, padx=5)

log_console = scrolledtext.ScrolledText(frame, wrap=tk.WORD, height=20, bg="#002b36", fg="#00FF00")
log_console.pack(fill=tk.BOTH, expand=True)

log("GUI ready. Click ▶️ to start.")

root.mainloop()
# This script provides a GUI for the LunoSimBot, allowing users to start, pause, and manage the bot.
# It includes buttons for starting the bot, pausing it, backing up the wallet, opening the export file, and opening the bot folder.
# The log console displays messages from the bot and user actions.
# The bot logic is simulated with a placeholder function that runs in a separate thread to avoid blocking the GUI.
# The script uses tkinter for the GUI and handles file operations with standard Python libraries.
# Make sure to adjust the paths if your bot files are located elsewhere.
# The GUI is designed to be user-friendly and provides real-time feedback in the log console.
# Ensure you have the necessary permissions to read/write files in the specified directories.
# The script is intended to be run in a Python environment with tkinter installed.
# The GUI will not open a command prompt window, as it uses pythonw.exe to run the GUI application.
# This allows for a cleaner user experience without the console window appearing.
# The script is designed to be run from a batch file that sets the working directory to the GUI folder.
# The batch file should be placed in the same directory as this script to ensure it runs correctly.
# The GUI is responsive and allows for easy interaction with the bot's functionality.
# The log messages are color-coded for better visibility, using a dark background with green text.
# The script is compatible with Python 3.x and requires the tkinter library, which is included  