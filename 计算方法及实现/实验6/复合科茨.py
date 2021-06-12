import numpy

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
    return x ** 8

# 上下限
a = 3
b = 6
# 次数
cishu = 5
# 步长
step = 1


if __name__ == '__main__':
    print("求积公式：f(x) = x ** 8 上限：{} 下限：{} 次数：{} 步长：{}".format(a, b, cishu, step))
    sum = 0
    # 进行分段
    jieduan = list(numpy.arange(a, b+step, step))
    fenzu = zip(jieduan[:-1],jieduan[1:])
    # 每段进行求积分
    for zu in fenzu:
        top = zu[1]
        bot = zu[0]
        tempa = (top - bot) / cishu
        for i, index in enumerate(cotesCoef[cishu]):
            # print("sum+=f({}+{}*{})*{}".format(bot, tempa, i, index))
            sum += f(bot + tempa * i) * index
    print(sum)

