defmodule Tenta do
  def sum(nil) do 0 end
  def sum({node, int, left, right}) do
    int + sum(left) + sum(right)
  end
  def testSum() do sum{:node, 5, {:node, 3, nil, nil},{:node, 2, nil, nil}} end

  def mirror(nil) do nil end
  def mirror({:node, int, left, right}) do
    {:node, int, mirror(right), mirror(left)}
  end

  def testMirror() do mirror({:node, 5, {:node, 3, nil, nil},{:node, 2, nil, nil}}) end

def add(nil, a) do {:node, a, nil, nil} end
def add({:node, int, treeA, treeB}, a) do
  if a < int do {:node, int, add(treeB, a), treeA}
  else {:node, a, add(treeB, int), treeA}
  end
end

  def fizzbuzz(n) do fizzbuzz(1, n+1, 1, 1) end

  def fizzbuzz(last, last, _, _) do [] end
  def fizzbuzz(next, last, 3, 5) do [:fizzbuzz | fizzbuzz(next + 1, last, 1, 1)] end
  def fizzbuzz(next, last, a, 5) do [:buzz | fizzbuzz(next + 1, last, a + 1, 1)] end
  def fizzbuzz(next, last, 3, b) do [:fizz | fizzbuzz(next + 1, last, 1, b + 1)] end
  def fizzbuzz(next, last, x, y) do [next | fizzbuzz(next + 1, last, x + 1, y + 1)] end


  def reduce(nil, init, op) do init end
  def reduce({node, value, l, r}, init, op) do op.( op.(reduce(l, init, op), value), reduce(r, init, op) ) end

  def to_list(fuckface, op) do List.flatten(reduce(fuckface, [], op)) end



def sum(nil) do 0 end
def sum({:node, int, left, right}) do
  int + sum(left) + sum(right)
end

end
