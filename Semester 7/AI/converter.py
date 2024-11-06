# Collect input from the user  
kilometers = float(input('How many kilometers?: '))  
conv_fac = 0.621371  # conversion factor  
miles = kilometers * conv_fac  # calculate miles  
print('%0.3f kilometers is equal to %0.3f miles' %(kilometers,miles))  