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
