#!/system/bin/sh

# Track the state so we only run ethtool once per plug-in
adapter_present=0

while true; do
    # Check if the interface exists
    if [ -d "/sys/class/net/eth0" ]; then
        if [ "$adapter_present" -eq 0 ]; then
            # Wait a brief moment to let the system fully initialize the interface
            sleep 1

            # Disable TSO, GSO, and GRO for better performance with certain USB Ethernet adapters (ex: ASIX AX88179)
            ethtool -K eth0 tso off gso off gro off

            # Mark as present so we don't spam the command
            adapter_present=1
        fi
    else
        # Adapter was unplugged, reset the state
        adapter_present=0
    fi

    # Sleep for 3 seconds before checking again
    sleep 3
done
