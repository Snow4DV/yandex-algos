package stuff


class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    fun encode(strs: List<String>): String {
        return strs.map {"${strs.size};$it"}.joinToString("")
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */

}