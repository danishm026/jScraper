#!/bin/bash

use_file=false
while getopts "m:f:" opt
do
	case $opt in
		m)	
			name=${OPTARG}
			;;
		f)	
			file=${OPTARG}
			use_file=true
			;;
	esac
done

#Change to jScraperDao directory
cd ../jScraperDao

#Clean and install jScraper Dao
mvn clean install

#Change back to jScraper directory
cd ../jScraper/

#Clean and Compile jScraper
mvn clean compile

#Create output filename
filename=$( echo $name | sed -e "s/\b\(.\)/\u\1/g"| tr " " _)

#Create directory for model
mkdir -p output/$filename

if [ $use_file = false ]
then
	#Run scraper with name as input and send output to temporary file tmp
	mvn exec:java -Dexec.mainClass="com.arc.jScraper.main.Main" <<<$name | stdbuf -o0 grep "^http" > output/$filename/$filename

	#Change to model directory
	cd output/$filename/

	#Download Images
	wget --user-agent="" --input-file=$filename --continue 
fi


