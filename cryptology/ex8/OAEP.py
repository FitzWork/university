import hashlib
import random


mod_n = 0
message = 0


def mgf(seed, length):
    t = []
    counter = 0
    repeat = True
    while repeat:
        counter += 1
        t = t.append(hashlib.sha1(int(str(seed) + str(counter.rjust(32)))))
        if len(t) > length:
            repeat = False
    out = bytes
    for i in range(length):
        out = str(out) + str((t[i]))
    return out


def transformation(n, m):
    lgt = [bytes]
    #                                  2 * len(sha1)
    assert len(bytes(m)) <= len(bytes(n)) - 32 - 2
    lhash = hashlib.sha1(lgt)
    tmp = len(bytes(n)) - len(bytes(m)) + 34
    ps = 0
    xor = str(lhash) + str(ps.rjust(tmp)) + str(0x01) + str(bytes(m))
    seed = random.randbytes(16)

    # bytes(a ^ b for a, b in zip(mgf(seed, lgt), xor))
    masked_db = int(mgf(seed, lgt)) ^ int(xor)
    back_mgf = mgf(masked_db, lgt)
    masked_seed = int(seed) ^ int(back_mgf)
    rsa_input = str(0x00) + str(bytes(masked_seed)) + str(masked_db)
