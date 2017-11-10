import csv
data = csv.reader(open('students.csv'), delimiter=",", quotechar='|')
Party, Smart, Creative, HW, Mac, Project, Success, Happy = [],[],[],[],[],[],[],[]
print(data)
countOneOne=0
numRow = 0 # count number of part 1/0 and smart 1/0 and Hw 1/0
count = 0 #number of party 1/0 and smart 1/0
percentOneOne=0.0
for x in data:
	numRow= numRow +1
	if(x[0] == "1" and x[4] == "1" and x[6] == "1" and x[7] == "1"):
		#print(x[0] + " " + x[1] + " " + x[3] + " ")
		countOneOne = countOneOne + 1
	if(x[0] == "1" and x[4] == "1" and x[6] == "1"):
		#print(x[0] + " " + x[1] + " " + x[3] + " ")
		count = count+ 1

print(countOneOne)
percentOneOne = countOneOne/count
print(percentOneOne)