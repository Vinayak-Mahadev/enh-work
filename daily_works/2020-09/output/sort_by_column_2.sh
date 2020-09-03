# author - Vinayak Mahadev

echo "Input file --->analytics_table_size_for_interface.csv" >> ./sorting_log.txt
echo "Output file --->$2" >> ./sorting_log.txt
sort -t '|' -k1 -k2 analytics_table_size_for_interface.csv -o analytics_table_size_for_interface_sorted.csv >> ./sorting_log.txt

