
# mit allen und mit cordinalitionen

# metriken schreiben, wie und was

# template

from sklearn import svm
import pandas as pd 

machine_learning = svm.SVC()
features = pd.read_csv('result.csv').values
predicion = ["c2d","cachet","countAntom","d4","dSharp","ganak","miniC2D","sharpSAT"]
machine_learning.fit(features,predicion)
machine_learning.predicion([])

