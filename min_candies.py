# Alice is a kindergarten teacher. She wants to give some candies to the children in her class. 
# All the children sit in a line (their positions are fixed), 
# and each of them has a rating score according to his or her performance in the class. 
# Alice wants to give at least 1 candy to each child. 
# If two children sit next to each other, then the one with the higher rating must get more candies. 
# Alice wants to save money, so she needs to minimize the total number of candies given to the children.


def candies(ratings):
    length = len(ratings)
    if length == 0:
        return 0
    
    sum = 1 # the candy for the 1st kid
    curCandy = 1 # the number of candy for the current kid
    
    for i in range(1, length):
        if ratings[i] > ratings[i - 1]:
            curCandy = curCandy + 1
        elif i < length - 1 and ratings[i + 1] < ratings[i]:
            curCandy = 2
        else:
            curCandy = 1
        
        sum = sum + curCandy

    return sum
