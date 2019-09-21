import java.util.ArrayList;
import java.util.List;

public class Solution350 {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};
        System.out.println(new Solution350().intersect(nums1, nums2));
    }

    public List<Integer> intersect(int[] nums1, int[] nums2) {
        BSTMap<Integer,Integer> map = new BSTMap<>();
        for (int i:nums1) {
            if (!map.contains(i)) {
                map.add(i, 1);
            } else {
                map.set(i, map.get(i)+1);
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i:nums2) {
            if (map.contains(i)) {
                list.add(i);
                map.set(i, map.get(i) - 1);
                if (map.get(i) == 0)
                    map.remove(i);
            }
        }
        return list;
    }
}
