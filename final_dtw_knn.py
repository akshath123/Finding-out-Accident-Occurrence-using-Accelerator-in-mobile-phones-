from dtw import dtw
import numpy as np
import pandas as pd
import sys
import random

crash_dict = {}
crash_dict_label = []
crash_dict_test = {}
crash_dict_test_label = []

normal_dict = {}
normal_dict_label = []
normal_dict_test = {}
normal_dict_test_label = []

# Finding out the magnitude of acceleration 
def magnitude(datas,lim):
    mag_crash = {}
    for x in range(0, lim):    
        mag_crash[x] = pd.DataFrame(np.sqrt(np.power(datas[x].iloc[:, 0], 2) + np.power(datas[x].iloc[:, 1], 2) + np.power(datas[x].iloc[:, 2], 2)))
    return mag_crash

# Segrating the dataset into test set, Labels and training set
for i in range(40):
    crash_dict[i] = pd.read_csv("dat/accidents/acci (" +str(i+1) +").txt", sep = " ", nrows = 400)
magcrash = magnitude(crash_dict, 40)

for i in range(40):
    crash_dict_label.append('A')

for i in range(40,48):
    crash_dict_test[i-40] = pd.read_csv("dat/accidents/acci (" +str(i) +").txt", sep = " ", nrows = 400)
magcrash_test = magnitude(crash_dict_test, 8)

for i in range(8):
    crash_dict_test_label.append('A')

for i in range(40,48):
        normal_dict_test[i-40] = pd.read_csv("dat/normal/norm (" +str(i) +").txt", sep = " ", nrows = 400)
magnoral_test = magnitude(normal_dict_test, 8) 

for i in range(8):
    normal_dict_test_label.append('N')

for i in range(40):
        normal_dict[i] = pd.read_csv("dat/normal/norm (" +str(i+1) +").txt", sep = " ", nrows = 400)
magnoral = magnitude(normal_dict, 40)
 
for i in range(40):
    normal_dict_label.append('N')

# Feature Scaling 
from sklearn.preprocessing import MinMaxScaler
sc = MinMaxScaler()

for i in range(0, 40):
    magcrash[i] = sc.fit_transform(magcrash[i])
    
for i in range(0, 40):
    magnoral[i] = sc.fit_transform(magnoral[i])

for i in range(0, 8):
    magcrash_test[i] = sc.fit_transform(magcrash_test[i])
    
for i in range(0, 8):
    magnoral_test[i] = sc.fit_transform(magnoral_test[i])

dist_tot = [] 

def cal_sim(x, y):
    dist, _, _, _ = dtw(x, y, dist=lambda x, y: np.linalg.norm(x - y, ord=1))
    return dist 

# Prediction 
def predict(test):
    dist_tot = []
    for i in range(len(magcrash)-30):        
        dist1 = cal_sim(magcrash[i], test[0])            
        dist_tot.append(dist1)
      
    for i in range(len(magnoral)-30):        
        dist1 = cal_sim(magnoral[i], test[0])            
        dist_tot.append(dist1)
    
    return dist_tot
      
dist_tot = predict(magnoral_test[3])
dist_tot = np.array(dist_tot)                
Label = [crash_dict_label, normal_dict_label]  
Z = [l for (d,l) in sorted(zip(dist_tot,Label), key=lambda pair: pair[0])]     
knn_ind = Z[0][0]

if( knn_ind == 'A'):
    print("Accident")
else:
    print("No Accident")