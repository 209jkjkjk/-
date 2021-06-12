import time

import numpy

class State:
    def __init__(self, state):
        self.state = state
        self.row = state.shape[0]
        self.col = state.shape[1]
        self.ID = self.getID()

    def getID(self):
        ID = 0
        b = pow(10, self.row * self.col - 1)
        for i in range(self.row):
            for j in range(self.col):
                ID += b * self.state[i][j]
                b /= 10
        return int(ID)

    def renewID(self):
        ID = 0
        b = pow(10, self.row * self.col - 1)
        for i in range(self.row):
            for j in range(self.col):
                ID += b * self.state[i][j]
                b /= 10
        self.ID = int(ID)

    # 计算代价
    def getCost(self, answerDict):
        cost = 0
        for i in range(self.row):
            for j in range(self.col):
                # 对于每一个位置，先找到当前位置的值
                cur = self.state[i][j]
                # 找到值应该所在的位置
                x, y = answerDict.get((cur,))
                cost += (abs(x - i) + abs(y - j))
                # print(cur, x, y, i, j, cost)
        return cost

    # 获取0点位置
    def getZeroPos(self):
        postion = numpy.where(self.state == 0)
        return postion[0][0], postion[1][0]

    def __eq__(self, other):
        return self.ID == other.ID


def nextSteps(current, answerDict, closeSet, openList):
    x, y = current.getZeroPos()
    possibilities = []
    for i in range(-1, 2):
        for j in range(-1, 2):
            if abs(i - j) != 1:
                continue
            if not 0 <= x + i <= 2 or not 0 <= y + j <= 2:
                continue
            # 拷贝当前情况
            newarray = current.state.copy()
            # 交换数据（进行移动）
            newarray[x][y], newarray[x+i][y+j] = newarray[x+i][y+j], newarray[x][y]
            # 转换成对象
            newState = State(newarray)
            # 查看是否在闭集合中
            if newState.ID in closeSet:
                continue
            possibilities.append((newState.getCost(answerDict), newState))
            # print((newState.getCost(answerDict), newState))

    return possibilities


if __name__ == "__main__":
    # 建立目标
    answer = State(numpy.array([[1, 2, 3], [4, 5, 6], [7, 8, 0]]))
    answerDict = {}
    for row, rowdata in enumerate(answer.state):
        for col, value in enumerate(rowdata):
            answerDict[(value,)] = (row, col)
    # 建立起点
    current = State(numpy.array([[4, 8, 6], [7, 0, 3], [2, 1, 5]]))
    # current = State(numpy.array([[1, 5, 3], [2, 4, 6], [7, 0, 8]]))
    # current = State(numpy.array([[1, 2, 3], [4, 5, 6], [7, 8, 0]]))
    openStateList = [0,]  # 为了第一次弹出
    closeIDSet = set()
    kk = 1
    while current != answer:
        # print(openStateList)
        openStateList.pop()
        # print(len(openStateList))
        print(kk)
        print(current.state, end="\n\n\n")
        # if kk % 100 == 0:
        #     print(len(openStateList))
        #     # print(current.state)
        kk += 1
        possibilities = nextSteps(current, answerDict, closeIDSet, openStateList)
        openStateList.extend(possibilities)
        openStateList.sort(key=lambda x: x[0], reverse=True)
        # print(openStateList)
        # 加入闭集合
        closeIDSet.add(current.ID)
        # 选择下一步
        current = openStateList[-1][1]
        # input()




    print("over")
