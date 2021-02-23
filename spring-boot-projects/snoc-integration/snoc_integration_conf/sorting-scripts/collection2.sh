# author - Suresh Upparu

echo "Input file --->$1" >> /home/appuser/file_job/sort/sorting_log.txt
echo "Output file --->$2" >> /home/appuser/file_job/sort/sorting_log.txt
sort -t '|' -k 1 -k 2 $1 -o $2 >> /home/appuser/file_job/sort/sorting_log.txt
