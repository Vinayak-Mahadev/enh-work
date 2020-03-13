# author - Suresh Upparu

echo "Input file --->$1" >> /home/appuser/snoc/snocconf/interfaceconf/sort/sorting_log.txt
echo "Output file --->$2" >> /home/appuser/snoc/snocconf/interfaceconf/sort/sorting_log.txt
sort -t '|' -k 1 $1 -o $2 >> /home/appuser/snoc/snocconf/interfaceconf/sort/sorting_log.txt
