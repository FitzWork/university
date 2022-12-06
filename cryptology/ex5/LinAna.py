import string
from os import urandom


def s_box(text_block):
    box = {0xe, 0x4, 0xd, 0x1, 0x2, 0xf, 0xb, 0x8, 0x3, 0xa, 0x6, 0xc, 0x5, 0x9, 0x0, 0x7}

    for i in range(len(text_block)):
        text_block = text_block[:i] + box[text_block[i]] + text_block[i + 1:]

    return text_block


def permutation(text_block):
    for i in text_block:
        assert i in string.hexdigits

    text_block = ''.join([(bin(int(text_block[i], 16))[2:]).zfill(4) for i in range(len(text_block))])
    flag_list = []
    prm = [1, 5, 9, 13, 2, 6, 10, 14, 3, 7, 11, 15, 4, 8, 12, 16]

    for i in range(len(text_block)):
        if i not in flag_list:
            temp = text_block[i]
            text_block = text_block[:i] + text_block[prm[i]] + text_block[i + 1:]
            text_block = text_block[:prm[i]] + temp + text_block[(prm[i] + 1):]
            flag_list.append(temp)
            flag_list.append(prm[i])
        else:
            continue

    return (hex(int(text_block, 2))[2:]).zfill(4)


def key_add(text_block):
    text_block = text_block.decode("hex")
    key = urandom(2)
    return_text = ""

    for i in range(len(text_block)):
        return_text += chr(ord(key[i]) ^ ord(text_block[i]))

    return return_text.encode("hex")


def encrypt(plaintext):
    plaintext = plaintext.encode("hex")
    tmp = plaintext

    for i in range(0, len(tmp), 4):
        for j in range(3):
            plaintext = plaintext[:i] + key_add(plaintext[i:i+4]) + plaintext[i+4:]
            plaintext = plaintext[:i] + s_box(plaintext[i:i+4]) + plaintext[i+4:]
            plaintext = plaintext[:i] + permutation(plaintext[i:i+4]) + plaintext[i+4:]

        plaintext = plaintext[:i] + key_add(plaintext[i:i+4]) + plaintext[i+4:]
        plaintext = plaintext[:i] + s_box(plaintext[i:i+4]) + plaintext[i+4]
        plaintext = plaintext[:i] + key_add(plaintext[i:i+4]) + plaintext[i+4:]
    return plaintext


# ----- decryption -----


def spn(text, pw):
    for count in range(3):
        text ^= pw
        text = s_box(text)
        text = permutation(text)

    text ^= pw
    text = s_box(text)
    text ^= pw

    return text
