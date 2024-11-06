def swapPositions(list, pos1, pos2):
    list[pos1], list[pos2] = list[pos2], list[pos1]
    return list
values = input("Input some comma seprated numbers : ")
list = values.split(",")  # convert to list
print('Original list is : ', list)
pos1, pos2 = 1, 3
print('Swaped list is : ',swapPositions(list, pos1-1, pos2-1))
