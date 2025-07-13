import tkinter as tk
from tkinter import ttk, messagebox, scrolledtext
import subprocess, threading, os, json

# === CONFIG ===
SCRIPT_DIR = r"C:\Bots\LunoSimBot"
WALLET_FILE = os.path.join(SCRIPT_DIR, "wallet", "wallet.json")

DEFAULT_WALLET = {
    "BTC": 0.0001,
    "ETH": 0.0,
    "ZAR": 1000.0
}

# === SELF-HEALING FUNCTIONS ===
def ensure_wallet_file():
    wallet_dir = os.path.dirname(WALLET_FILE)
    os.makedirs(wallet_dir, exist_ok=True)
    if not os.path.exists(WALLET_FILE):
        with open(WALLET_FILE, 'w') as f:
            json.dump(DEFAULT_WALLET, f, indent=4)
        log_message("⚠️  Wallet file was missing. A new one was created.\n")

def load_wallet():
    ensure_wallet_file()
    try:
        with open(WALLET_FILE, 'r') as f:
            return json.load(f)
    except json.JSONDecodeError:
        with open(WALLET_FILE, 'w') as f:
            json.dump(DEFAULT_WALLET, f, indent=4)
        log_message("⚠️  Wallet file was corrupted. Reset to defaults.\n")
        return DEFAULT_WALLET.copy()

def save_wallet(data):
    with open(WALLET_FILE, 'w') as f:
        json.dump(data, f, indent=4)

# === GUI ACTIONS ===
def run_powershell_script(script_relative_path):
    full_path = os.path.join(SCRIPT_DIR, script_relative_path)
    if not os.path.isfile(full_path):
        messagebox.showerror("Script Not Found", f"{script_relative_path} is missing.")
        log_message(f"[ERROR] {script_relative_path} not found.\n")
        return

    def task():
        log_message(f"--- Running {script_relative_path} ---\n")
        proc = subprocess.Popen(
            ["powershell.exe", "-ExecutionPolicy", "Bypass", "-File", full_path],
            stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True
        )
        out, err = proc.communicate()
        if out:
            log_message(out)
        if err:
            log_message(f"[ERROR]: {err}\n")
        log_message(f"--- Completed {script_relative_path} ---\n\n")

    threading.Thread(target=task).start()

def open_wallet_editor():
    wallet = load_wallet()
    editor = tk.Toplevel(root)
    editor.title("Wallet Editor")
    editor.geometry("300x250")
    ttk.Label(editor, text="Edit Wallet Balances", font=("Arial", 12, "bold")).pack(pady=10)

    fields = {}
    for symbol, value in wallet.items():
        frame = ttk.Frame(editor, padding=5)
        frame.pack(fill='x')
        ttk.Label(frame, text=symbol).pack(side='left')
        entry = ttk.Entry(frame)
        entry.insert(0, str(value))
        entry.pack(side='right', expand=True, fill='x')
        fields[symbol] = entry

    def save_changes():
        new_wallet = {}
        for symbol, entry in fields.items():
            val = entry.get().replace(',', '.')
            try:
                new_wallet[symbol] = float(val)
            except ValueError:
                messagebox.showerror("Invalid Input", f"{symbol}: {val} is not a number.")
                return
        save_wallet(new_wallet)
        log_message("💾 Wallet updated via GUI.\n")
        messagebox.showinfo("Success", "Wallet saved successfully.")
        editor.destroy()

    ttk.Button(editor, text="Save", command=save_changes).pack(pady=10)

# === GUI BOOTSTRAP ===
root = tk.Tk()
root.title("LunoSimBot GUI v0.4")
root.geometry("850x500")

style = ttk.Style()
style.theme_use('clam')

mainframe = ttk.Frame(root, padding="10")
mainframe.pack(fill='both', expand=True)

# Left Panel: Buttons
button_frame = ttk.LabelFrame(mainframe, text="Controls", padding="10")
button_frame.pack(side='left', fill='y', padx=5)

buttons = [
    ("Healthcheck",    r"doctor\healthcheck.ps1"),
    ("View Wallet",    r"modules\wallet_view.ps1"),
    ("Fetch Prices",   r"modules\simulator.ps1"),
    ("Update Wallet",  "WALLET_EDITOR"),
    ("Run Strategy",   r"modules\core.ps1"),
    ("Monitor Alerts", r"modules\summary.ps1"),
    ("View Logs",      r"modules\view_logs.ps1"),
    ("Export Logs",    None)
]

for label, path in buttons:
    action = (
        lambda p=path: open_wallet_editor()
        if p == "WALLET_EDITOR"
        else (lambda: log_message("Feature coming soon.\n") if not p else run_powershell_script(p))
    )
    ttk.Button(button_frame, text=label, width=20, command=action()).pack(pady=3)

# Right Panel: Logs
log_frame = ttk.LabelFrame(mainframe, text="Log Output", padding="10")
log_frame.pack(side='right', fill='both', expand=True, padx=5)

log_text = scrolledtext.ScrolledText(log_frame, state="disabled", wrap="word", font=("Consolas", 10), fg="green", bg="black")
log_text.pack(fill="both", expand=True)

def log_message(message):
    log_text.configure(state='normal')
    log_text.insert(tk.END, message)
    log_text.configure(state='disabled')
    log_text.see(tk.END)

# First Log Entry
ensure_wallet_file()
log_message("🟢 GUI started. Wallet file verified.\n")

# Start GUI
root.mainloop()
