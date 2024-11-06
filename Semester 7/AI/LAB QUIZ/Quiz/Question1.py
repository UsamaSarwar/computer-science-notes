import sys

def main():
    transition = [[[1],[2]], [[5],[4]], [[5],[3]], [[4],[2]]]
    userInput = input("Input String: ")
    userInput = list(userInput) 
    for index in range(len(userInput)): 
        if userInput[index]=='a':
            userInput[index]='0' 
        else:
            userInput[index]='1'
    final = "4" 
    start = 0
    i=0 
    trans(transition, userInput, final, start, i)
    print ("rejected")

def trans(transition, userInput, final, state, i):
    for j in range (len(userInput)):
        for each in transition[state][int(userInput[j])]:
            if each < 4:                             
                state = each
                if j == len(userInput)-1 and (str(state) in final): 
                    print ("accepted")
                    sys.exit()
                trans(transition, userInput[i+1:], final, state, i) 
        i = i+1 

if __name__ == '__main__':
    main()