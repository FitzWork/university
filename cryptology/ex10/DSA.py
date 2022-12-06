import random
import hashlib


def gen_bits(length):
    while True:
        number = random.getrandbits(length)
        if number.bit_length() == length:
            return number


def euklid(a, b):
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
    return p[k]


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


def prim_generate(n):
    z = gen_bits(n)
    i = [1, 7, 11, 13, 17, 19, 23, 29]

    while True:
        for index in i:
            p = z + index
            if p.bit_length() == n:
                if miller_rabin(p):
                    return p

        z += 30
        if z.bit_length() > n:
            z = gen_bits(n)


# -----


def params(n, l):
    q = prim_generate(l)

    repeats = 0
    end = False
    while not end:
        if repeats % 50 == 0:
            q = prim_generate(160)

        k = gen_bits((n - 1) / l)
        p = k * q + 1

        if p.bit_length() == 1024 and miller_rabin(p):
            end = True

            for _ in range(50):
                if not miller_rabin(p):
                    end = False
                    break

    end = False
    while not end:
        h = random.randrange(2, p - 2)
        g = pow(h, (p - 1) / q, p)

        if g != 1:
            end = True

    return p, q, g


def signature(p, q, g, m, x):
    r = int

    while True:
        j = random.randrange(2, q - 1)
        r = pow(g, j, p) % q

        if r != 0:
            inv_j = euklid(j, q) % q
            s = (inv_j * (int(hashlib.sha1(m).digest()) - r * x)) % q

            if s != 0:
                return [r, s]


def verify(p, q, m, y, r, s):
    if not (0 < r < q and 0 < s < q):
        return False

    w = euklid(s, q) % q
    u1 = (int(hashlib.sha1(m).digest()) * w) % q
    u2 = (r * w) % q
    v = ((pow(y, u1) * pow(y, u2)) % p) % q
    if v != r:
        return False

    return True


if __name__ == '__main__':
    (p, q, g) = params(1024, 160)

    x = random.randrange(2, q - 1)
    y = pow(g, x, p)
    m = "Test String"

    (r, s) = signature(p, q, g, m, x)
    verify(p, q, m, y, r, s)
