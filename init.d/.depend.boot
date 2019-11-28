TARGETS = console-setup mountkernfs.sh resolvconf ufw plymouth-log hostname.sh apparmor screen-cleanup udev keyboard-setup cryptdisks cryptdisks-early networking urandom hwclock.sh checkroot.sh lvm2 mountdevsubfs.sh open-iscsi iscsid checkfs.sh mountall.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountnfs.sh bootmisc.sh mountall-bootclean.sh kmod procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: mountkernfs.sh urandom resolvconf procps
urandom: hwclock.sh
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
lvm2: cryptdisks-early mountdevsubfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
open-iscsi: networking iscsid
iscsid: networking
checkfs.sh: cryptdisks checkroot.sh lvm2
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
bootmisc.sh: mountnfs-bootclean.sh checkroot-bootclean.sh mountall-bootclean.sh udev
mountall-bootclean.sh: mountall.sh
kmod: checkroot.sh
procps: mountkernfs.sh udev
