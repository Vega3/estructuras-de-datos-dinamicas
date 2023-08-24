#Implementacion Slicing
def custom_slice(sequence, start=None, stop=None, step=None):
    return sequence[start:stop:step]


#Cree una funcion recursiva que reciba un numero entero y retorne cuántos digitos
#de este numero son multiplos de 2 y de 4
def multiples(n):
    if n < 10:
        if n % 2 == 0 and n % 4 == 0:
            return 1
        else:
            return 0
    last_digit = n % 10
    if last_digit == 0:
        return multiples(n // 10)
    if last_digit % 2 == 0 and last_digit % 4 == 0:
        return 1 + multiples(n // 10)
    else:
        return multiples(n // 10)

#caso de prueba
resultado = multiples(12345678)
print("Caso prueba punto 1: ", resultado)


#Cree una funcion no recursiva que invierta solo la primera mitad de un string
def invert_second_half(input_str):
    length = len(input_str)
    middle = length // 2
    second_half = custom_slice(input_str, start=middle)
    inverted_second_half = custom_slice(second_half, step=-1)
    result = custom_slice(input_str, stop=middle) + inverted_second_half
    return result

#Caso de prueba
output_string = invert_second_half("Estructuras")
print("caso prueba punto 2: ", output_string)


#Cree una funcion recursiva que calcule las sumatoria de todos los numeros impares de 
# una matrix cuadrada.
def matrix_sum(matrix, row=0, col=0):
    if row >= len(matrix):
        return 0
    current_element = matrix[row][col]
    rest_sum = matrix_sum(matrix, row + (col + 1) // len(matrix), (col + 1) % len(matrix))
    if current_element % 2 != 0:
        return current_element + rest_sum
    else:
        return rest_sum

#Caso prueba
matrix = [
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
]

result = matrix_sum(matrix)
print("Caso de prueba punto 3:", result) 


#Cree una funcion que recursiva que resiva una lista, un elemento y un indice
#y retorne si el elemento esta en la lista en la posicion dada

def element_position(l, e, i):
    if i >= len(l) or i < 0:
        return False
    if l[i] == e:
        return True
    else:
        return element_position(l, e, i + 1)

#Caso de prueba
my_list = [1, 2, 3, 4, 5]
element = 3
index = 5

result = element_position(my_list, element, index)
print("Caso de prueba punto 4: ", result)


#Cree una funcion recursiva que reciba un string alfanumerico y que extraiga
#los digitos del string y retorne el número formado por todos los digitos

def extraact_digits(s, i=0, n=0):
    if i >= len(s):
        return n
    if s[i].isdigit():
        digit = int(s[i])
        n =  n * 10 + digit
    return extraact_digits(s, i + 1, n)

#Caso de prueba
result = extraact_digits("DSasdD213SSA31Sa")
print("Caso de prueba punto 5: ", result)
    
