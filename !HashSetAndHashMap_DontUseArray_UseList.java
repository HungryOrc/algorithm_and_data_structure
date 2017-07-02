// HashSet 和 HashMap 里，如果要装几个同类元素所组成的Collection，要用 List ！！！ 不要用 Array ！！！
// 因为：
//
// Arrays use the default identity-based Object.hashCode() implementation and 
// there's no way you can override that. 
// So don't use Arrays as keys in a HashMap/HashSet ！！！
//
// the equals() method of List had already been overridden ！！！
// so we don't need to worry about it ！！！
// it is comparing the actual elements' values in the List
