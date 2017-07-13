/* Laioffer 总结

Given a set of n items, each item has a weight of w[i], i = 1, 2... n. The knapsack has a weight capacity of W.
What's the max total weight you can put into the knapsack?

1. Naive 0/1 Knapsack Problem:
    1.1 Max total weight:
        M[i, w] = M[i - 1, w] || M[i - 1, w - weights[i]]
    1.2 Get number of solutions:
        M[i, w] = M[i - 1, w] + M[i - 1, w - weights[i]]
    1.3 Use minimum number of items:
        M[i, w] = min(M[i - 1, w], M[i - 1, w - weights[i]] + 1)
