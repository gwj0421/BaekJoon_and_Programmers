def quadratic_formula(a,b,c):
    return [(-b+(b**2-4*a*c)**0.5)/(2*a),(-b-(b**2-4*a*c)**0.5)/(2*a)]
    
def solution(brown, yellow):
    temp = quadratic_formula(2,-brown-4,2*(brown+yellow))
    return temp