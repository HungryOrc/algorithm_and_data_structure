/* There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example
Given n = 2, prerequisites = [[1,0]]
Return true
Given n = 2, prerequisites = [[1,0],[0,1]]
Return false */

public class Solution {
    
    /* @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return true if can finish all courses or false */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        int[] numOfPreCourses = new int[numCourses];
        // Array of ArrayLists
        // 注意格式！！在此，ArrayList后面不要加 <> 或者 <Integer> ！！
        // 所以在此声明的每一个数组元素，即每一个ArrayList里面的东西都是 Object
        // 在后面的使用中，要把这些Object显性转换为int类型
        ArrayList[] subCourses = new ArrayList[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            subCourses[i] = new ArrayList<Integer>();
        }
        
        for (int i = 0; i < prerequisites.length; i++) {
            int preCourse = prerequisites[i][1];
            int subCourse = prerequisites[i][0];
            
            numOfPreCourses[subCourse] ++;
            subCourses[preCourse].add(subCourse);
        }
        
        Queue<Integer> manageCourses = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (numOfPreCourses[i] == 0) {
                manageCourses.offer(i);
            }
        }
        
        int numOfScheduledCourses = 0;
        
        while (!manageCourses.isEmpty()) {
            int course = manageCourses.poll();
            numOfScheduledCourses ++;
            
            for (int j = 0; j < subCourses[course].size(); j++) {
                int subCourse = (int)subCourses[course].get(j);
                
                // 注意！精华在这里！！！
                // 当一门课的所有pre courses都已被放入结果数组时，这门课也可以被放入了！！！
                numOfPreCourses[subCourse] --;
                if (numOfPreCourses[subCourse] == 0) {
                    manageCourses.offer(subCourse);
                }
            }
            
        }
        
        if (numOfScheduledCourses == numCourses) {
            return true;
        } else {
            return false;
        }
    }
}
