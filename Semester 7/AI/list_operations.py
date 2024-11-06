my_list = [1, "Hello", 3.4, 5,8,9]
print("List is:", my_list)
# nested list
n_list = ["mouse", [8, 4, 6], ['a']]
print("List is:", n_list)
# List indexing
print(my_list[0])
print(my_list[2])
print(my_list[4])
# Nested List
# Nested indexing
print(n_list[0][1])
print(n_list[1][2])

# List slicing in Python
# elements 3rd to 5th
print(my_list[2:5])
#elements beginning to 4th
print(my_list[:-5])
# elements 6th to end
print(my_list[5:])
# elements beginning to end
print(my_list[:])

# List Membership using in operator
print('mouse' in my_list)
print('a' in my_list)
print(9 not in my_list)

#iteration
for fruit in ['apple','banana','mango']:
    print("I like",fruit)
