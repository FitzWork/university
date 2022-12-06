import math
import random
import numpy
import datetime


print("Time: " + str(datetime.datetime.now().strftime('%H:%M:%S')))


def crypt(x, m, n):
    # print("encrypt")  # y = x^m mod n   (was ist mit negativen Exponenten)
    length = m.bit_length()
    b = [0] * length
    y = 1

    for i in range(len(b)):
        if m & 1:
            b[length - 1 - i] = 1
        else:
            b[length - 1 - i] = 0
        m >>= 1

    for i in range(len(b)):
        if b[i] == 1:
            y = (y * x) % n
        x = pow(x, 2, n)

    return y


def euklid(a, b):
    # print("euler")  # ggT(a,b) = sa + tb = c
    k = 0
    r = [a, b]
    p = [1, 0]
    q = [0, 1]

    while True:
        k += 1
        tmp = r[k - 1] // r[k]
        r.append(r[k - 1] - tmp * r[k])
        p.append(p[k - 1] - tmp * p[k])
        q.append(q[k - 1] - tmp * q[k])

        if r[k + 1] == 0:
            break
    return [r[k], p[k], q[k]]


message = 15
e_exponent = 53
modulo = 77
print("Zu verschlüsseln: " + str(message))

crypte = crypt(message, e_exponent, modulo)
print("Verschlüsselt: " + str(crypte))

# 77 = 11 * 7 -> \phi((11 - 1) * (7 - 1)) = 60
phi = (11 - 1) * (7 - 1)
eul = euklid(e_exponent, phi)
d_exponent = eul[1]

m2 = crypt(crypte, d_exponent, modulo)
print("Wieder entschlüsselt: " + str(m2))


def miller_rabin_once(n):
    m = n - 1
    k = 0

    while m % 2 == 0:
        k += 1
        m >>= 1

    a = random.randrange(2, n - 1)
    b = pow(a, m, n)

    if b == 1 % n:
        return True
    for _ in range(k):
        if b == -1 % n:
            return True
        b = pow(b, 2, n)
    return False


def miller_rabin(n):
    for _ in range(40):
        if not miller_rabin_once(n):
            return False
    return True


in_rab = 561
print(str(in_rab) + " is prim? " + str(miller_rabin(in_rab)))


def prim_generate():
    z = random.randint(pow(2, 100), pow(2, 500)) * 30
    i = [1, 7, 11, 13, 17, 19, 23, 29]

    while True:
        print("test")
        for index in i:
            p = z + index
            if p.bit_length() >= 100 and p <= 500:
                if miller_rabin(p):
                    return p

        z += 30
        if z.bit_length() > 500:
            z = random.randint(pow(2, 100), pow(2, 500)) * 30


e_exponent = random.randint(pow(2, 16), pow(2, 64))
print(str(message) + " mit " + str(e_exponent))
crypte = crypt(message, e_exponent, modulo)

# prims = prim_generate()
p = prim_generate()
q = prim_generate()
print(p, q)

# p = pow(2, 1279) - 1
# q = pow(2, 2203) - 1

n = p * q
phi = ((p - 1) * (q - 1))
eul = euklid(e_exponent, phi)
while eul[0] != 1:
    # p = q
    # q = prim_generator()
    # phi = ((p - 1) * (q - 1))

    e_exponent = random.randint(pow(2, 16), pow(2, 64))

    eul = euklid(e_exponent, phi)

d_exponent = eul[1]
m2 = crypt(crypte, d_exponent, modulo)
print(str(m2) + " mit " + str(d_exponent))
print("verschlüsseln mit:\ne: " + str(e_exponent) + "\nund\nn: " + str(n) + "\nverschlüsseln mit:\nd: " + str(d_exponent) + "\nund\nn: " + str(n))


def diff_of_quad(en):
    u = math.ceil(numpy.sqrt(en))
    while is_square(pow(u, 2) - en):
        u = u + 1
    w = numpy.sqrt(pow(u, 2) - en)
    return [u + w, u - w]


def is_square(number):
    while number != 1:
        if number == 0:
            return False
        number >> 1
    return True


u_and_w = diff_of_quad(n)
if u_and_w[0] == p and u_and_w[1] == q:
    print("p und q bestimmt")
elif u_and_w[1] == p and u_and_w[0] == q:
    print("p und q bestimmt")
else:
    print("p und q nicht bestimmmt")
