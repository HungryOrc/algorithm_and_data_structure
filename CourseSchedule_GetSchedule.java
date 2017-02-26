/* There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, 
return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. 
If it is impossible to finish all courses, return an empty array.

Example
Given n = 2, prerequisites = [[1,0]]
Return [0,1]
Given n = 4, prerequisites = [1,0],[2,0],[3,1],[3,2]]
Return [0,1,2,3] or [0,2,1,3] */

// Ref: http://www.jiuzhang.com/solutions/course-schedule-ii/
public class Solution {
    
    /* @param numCourses a total of n courses
     * @param prerequisites a list of prerequisite pairs
     * @return the course order */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
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
            int course = prerequisites[i][0];
            int preCourse = prerequisites[i][1];
            
            numOfPreCourses[course] ++;
            subCourses[preCourse].add(course);
        }
        
        Queue<Integer> manageCourses = new LinkedList<>();
        
        for (int i = 0; i < numCourses; i++) {
            if (numOfPreCourses[i] == 0) {
                manageCourses.offer(i);
            }
        }
        
        int[] courseSchedule = new int[numCourses];
        int order = 0;
        int numOfScheduledCourses = 0;
        
        while (!manageCourses.isEmpty()) {
            int course = manageCourses.poll();
            courseSchedule[order] = course;
            order ++;
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
            return courseSchedule;
        } else {
            return new int[0];
        }
        
    }
}
