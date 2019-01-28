
    // 
  
    public List<String> subSets(String set) {
      List<String> result = new ArrayList<>();
      if (set == null) {
        return result;
      }

      StringBuilder sb = new StringBuilder();
      getAllSubsets(set, 0, sb, result);
      return result;
    }

    private void getAllSubsets(String set, int curIndex, StringBuilder sb, List<String> result) {
      if (curIndex == set.length()) {
        result.add(sb.toString());
        return;
      }

      char curChar = set.charAt(curIndex);

      getAllSubsets(set, curIndex + 1, sb, result);

      sb.append(curChar);
      getAllSubsets(set, curIndex + 1, sb, result);
      sb.deleteCharAt(sb.length() - 1);
    }
  
}
