def Average(lst):
    return sum(lst) / len(lst)
list= [1,2,3,4,5] 
print('Original list is : ', list)
average = Average(list)
print("Average of the list =", round(average, 2))
