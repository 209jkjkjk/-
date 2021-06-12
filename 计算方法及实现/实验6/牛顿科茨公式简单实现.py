cotesCoef = {
    1: [1/2, 1/2],
    2: [1/6, 4/6, 1/6],
    3: [1/8, 3/8, 3/8, 1/8],
    4: [7/90, 16/45, 2/15, 16/45, 7/90],
    5: [19/288, 25/96, 25/144, 25/144, 25/96, 19/288],
    6: [41/840, 9/35, 9/280, 34/105, 9/280, 9/35, 41/840]
}


# 求积公式
def f(x):
    return x * x

# 上下限
a = 2
b = 30
# 次数
cishu = 6

if __name__ == '__main__':
    print("求积公式：f(x) = x * x 上限：{} 下限：{} 次数：{}".format(a, b, cishu))
    sum = 0
    tempa = (b - a) / cishu
    for i, index in enumerate(cotesCoef[cishu]):
        # print("sum+=f({}+{}*{})*{}".format(a, tempa, i, index))
        sum += f(a + tempa * i) * index
    print(sum)
