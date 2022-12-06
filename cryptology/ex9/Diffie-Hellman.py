import random


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


def prim_generate():
    z = random.randint(pow(2, 1000), pow(2, 2000)) * 30
    i = [1, 7, 11, 13, 17, 19, 23, 29]

    while True:
        for index in i:
            p = z + index
            if p.bit_length() >= 1000 and p <= 2000:
                if miller_rabin(p):
                    return p

        z += 30
        if z.bit_length() > 2000:
            z = random.randint(pow(2, 1000), pow(2, 2000)) * 30


def get_prim():
    p = int
    end = False

    while not end:
        q = prim_generate()
        p = q + q + 1

        if miller_rabin(p):
            end = True
    return p


def diffie():
    p = 23
    g = random.randrange(2, p - 2)
    a = random.randrange(2, p - 2)
    ga = pow(g, a, p)
    b = random.randrange(2, p - 2)
    gb = pow(g, b, p)

    alice = pow(gb, a, p)
    bob = pow(ga, b, p)

    print("Alice: " + str(alice))
    print("Bob: " + str(bob))
    print(alice == bob)


diffie()
