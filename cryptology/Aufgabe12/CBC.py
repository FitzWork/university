import Aufgabe03.AES as aes


def cbc(m, k):
    iv = 0

    for i in m:
        iv = aes.encrypt((m[i] ^ iv), k)

    return iv


def ccm(m, k, nonce, verify):
    x = [0] * 128

    for i in range(len(x)):
        if m & 1:
            x[127 - i] = 1
        else:
            x[127 - i] = 0
        m >>= 1

    t = []
    ctr = nonce << 64

    for i in range(len(x) + 1):
        t.append((ctr + i) % pow(2, m))

    y = []

    for i in range(1, len(x)):
        y.append(x[i - 1] ^ aes.encrypt(k, t[i]))

    tmp = cbc(x, k)
    y_ = t[0] ^ tmp

    y_e = int

    for i in y:
        y_e += str(y[i])

    y_e += y_

    if verify:
        return cbc(x, k) == y_ ^ t[0]
    else:
        return y_e
