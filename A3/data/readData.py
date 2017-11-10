import csv
data = csv.reader(open('tests.csv'), delimiter=",", quotechar='|')
Party, Smart, Creative, HW, Mac, Project, Success, Happy = [],[],[],[],[],[],[],[]

numRow = 0
countOne = 0
percent = 0.0
for row in data:
	Party.append(row[0])
	Smart.append(row[1])
	Creative.append(row[2])
	HW.append(row[3])
	Mac.append(row[4])
	Project.append(row[5])
	Success.append(row[6])
	Happy.append(row[7])

for one in Creative:
	numRow = numRow +1
	if(one == "1"):
		countOne = countOne +1
percent = countOne/numRow

print(Party)
countOneOne=0
percentOneOne=0.0
for x in data:
	print(x)
	if(x[0] == "1" and x[1] == "1" and x[3] == "1"):
		print("hhah")
		countOneOne = countOneOne + 1

percentOneOne = countOneOne/numRow

