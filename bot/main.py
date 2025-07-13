import json, time

CONFIG_PATH = 'config.json'
WALLET_PATH = 'wallet.json'

def run_bot():
    with open(CONFIG_PATH) as f:
        config = json.load(f)
    with open(WALLET_PATH) as f:
        wallet = json.load(f)
    print("Sim bot active. Press Ctrl+C to stop.")
    try:
        while True:
            print("Simulating trading activity...")
            time.sleep(2)
    except KeyboardInterrupt:
        print("Bot stopped.")