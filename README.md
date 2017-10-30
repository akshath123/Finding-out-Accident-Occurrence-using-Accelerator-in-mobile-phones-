# Motivation

There are many hardwares technologies which are present which assist a person during accidents for a four wheeler but yet the number people who have been saved are very less. Most of the accidents which happen are with 2 wheeler vehicles and some even with the pedistrians on the roads so those hardwares don't address generically and those are not affordable by everyone. Mobile phones are handy to everyone, everywhere. Accelerometer in mobile phones are very sensitive and can address these issues very well. The data from accelerometer have different patterns for a accidents, droping a phone by mistake, Pattern for hit by something. If all these patterns could be recoginized and then formed clusters based on this we could predict if an accident has occured. 

## How to implement?

The mobile phones accelerometer data will be feteched for every 2 mins and given to a clustering algorithm. The algorithm checks if any impact has occured which is fatal or not. If it finds it has fatal then it sounds an alarm in foreground for 30 seconds before sending the GEO location to relatives. Which inturn can call the helping bodies to assist the situation. 

## The Analytics

We are using **pattern recognization algorithm (Dynamic Time Wrapping Algorithm)** for finding out the distance between the signals ( the accelerometer data ) and cluster them using a **modified version of K-means clustering**. Here the distance metric we use is the distance what we get by comparing the two time series signals. 

## What happens if the user drops the phone by mistake

We also cluster the data for finding out if the mobile phones have been dropped because even those will also have a pattern even though if the analytics fail to group them in category of mobile phone has droppend then we will have a buffer time of 15 seconds and a siren will go on to the user saying that they have dropped the phone. If no response is given then only the message will go to the relatives with the geo cordinates of the user indicating the accidents. 

## NOTES: 
### install the dtw (dynamic time wrapping algorithm) package before using the code.
### This was made for Open Data Gov Hackathon. 
### For demonstration puprose we collected the data of the accelerometer by hitting on the wall while having the mobile phones and walking normally. We clustered them accordingly. 
