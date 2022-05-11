from scipy.stats import pearsonr
from scipy.stats import spearmanr

import pandas as pd
import csv

data1 = pd.read_csv('characteristics.csv')
data2 = pd.read_csv('all.csv')

rho_list = []
p_list = []

for col1 in data1.columns:
    for col2 in data2.columns:
        rho, p = spearmanr(data1[1:col1], data2[1:col2])
        rho_list.append(rho)
        p_list.append(p)

result = pd.DataFrame({'Rho':rho_list, 'P':p_list})
result.to_csv('result.csv', sep=',')